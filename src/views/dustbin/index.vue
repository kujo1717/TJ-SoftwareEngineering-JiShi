<template>
  <div>
    <div id="searchBox">
      <el-button
        @click="clearDustbin()"
        size="mini"
        type="danger"
      >清空回收站</el-button>
    </div>
    <el-table
      ref="table"
      :data="tableData.filter(data => !search || data.taskTitle.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
    >
      <el-table-column
        :key="currentInfoKey"
        label="删除日期"
        prop="isInDustbin"
      >
      </el-table-column>
      <el-table-column
        :key="currentInfoKey+1"
        label="事项名称"
        prop="taskTitle"
      >
      </el-table-column>
      <el-table-column
        :key="currentInfoKey+2"
        label="分组"
        prop="classificationTitle"
      >
      </el-table-column>
      <el-table-column
        :key="currentInfoKey+3"
        label="原定开始时间"
        prop="startTime"
      >
      </el-table-column>
      <el-table-column
        :key="currentInfoKey+4"
        label="原定结束时间"
        prop="endTime"
      >
      </el-table-column>

      <el-table-column align="right">
        <template
          slot="header"
          slot-scope="scope"
        >
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入关键字搜索"
          ></el-input>

        </template>

        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="handleRestore(scope.$index, scope.row)"
          >恢复</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleSmash(scope.$index, scope.row)"
          >彻底删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { getAllRubbish, smashOneRubbish, smashAllRubbish, restoreOneRubbish } from "@/api/dustbin.js"
export default {
  name: 'Dustbin',
  data () {
    return {
      search: '',
      currentInfoKey: 1,
      //需要一开始由后端赋值
      tableData: [{
        task_id: 100,
        date: '2016-05-02 12:00:00',
        name: '背单词',
        classification: '学习',
        timeRange: 'null'
      }, {
        task_id: 101,
        date: '2016-05-04 12:00:00',
        name: '玩原神',
        classification: '游戏',
        timeRange: 'null'
      }, {
        task_id: 102,
        date: '2016-05-01 12:00:00',
        name: '扫地',
        classification: '家务',
        timeRange: 'null'
      }, {
        task_id: 103,
        date: '2016-05-03 12:00:00',
        name: '上课',
        classification: '学习',
        timeRange: 'null'
      }],
    }
  },
  methods: {
    removeOneFromDustbin (task_id) {
      // let p1 = () => {
      //   return new Promise((res, rej) => {
      //     this.tableData = this.tableData.filter(item => {
      //       return item.task_id != task_id;
      //     })
      //   })
      // }
      // async function q1 () {
      //   let res = await p1();
      //   console.log("await结束！", res);
      //   this.currentInfoKey++;
      // }
      // q1();

      let newTableData = []
      for (let i = 0; i < this.tableData.length; i++) {
        if (this.tableData[i].id != task_id) {
          newTableData.push(this.tableData[i])
        }
      }
      this.tableData = newTableData;

      this.currentInfoKey += 1;


    },
    handleRestore (index, row) {
      console.log(index, row);
      console.log("row.id=", row.id)
      //请求后端恢复事项
      restoreOneRubbish(row.id)
        .then((res) => {
          console.log(res);
          //从前端回收站删除这一条数据
          this.removeOneFromDustbin(row.id);
          this.$message({
            type: 'success',
            message: '事项恢复成功！'
          });
        })
        .catch((err) => {
          console.log(err);
          this.$message({
            type: 'danger',
            message: '事项恢复失败'
          });
        })


    },
    handleSmash (index, row) {
      console.log(index, row);
      this.$confirm('您确定要永久删除事项吗？删除的事项不可恢复！', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(() => {
          //向后端请求删除...
          smashOneRubbish(row.id)
            .then((res) => {
              console.log(res);
              //从前端回收站删除这一条数据
              this.removeOneFromDustbin(row.id);
              this.$message({
                type: 'success',
                message: '事项永久删除成功！'
              });
            })
            .then((err) => {
              console.log(err);
              this.$message({
                type: 'danger',
                message: '事项删除失败'
              });
            })


        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '取消永久删除'
          })
        });

    },
    //清空回收站
    clearDustbin () {
      //确认信息
      this.$confirm('您确定要清空回收站吗？删除的事项不可恢复！', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(() => {
          //向后端请求删除...
          smashAllRubbish()
            .then((res) => {
              console.log(res);
              //清空前端回收站
              this.tableData = [];
              this.$message({
                type: 'success',
                message: '回收站清空成功！'
              });
            })
            .then((err) => {
              console.log(err);
              this.$message({
                type: 'danger',
                message: '回收站清空失败'
              });
            })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '取消清空回收站'
          })
        });
    },
    getFrontendTaskList () {
      console.log("开始拉取后端数据")
      //从后端拉取数据
      getAllRubbish()
        .then((res) => {
          let taskList = []
          //然后要把后端拉回来的task对象转换成前端要用的对象
          if (res.data != null || res.data != []) {
            console.log("res", res)
            let backendDataList = res.data;
            if (backendDataList == null)
              return;
            backendDataList.forEach((value) => {
              if (value.isInDustbin != '0') {
                taskList.push({
                  id: value.taskId,
                  taskTitle: value.taskTitle,
                  classificationTitle: value.classificationTitle,
                  isInDustbin: value.isInDusbin,
                  startTime: value.startTime,
                  endTime: value.endTime,
                  isInDustbin: this.timeTrans(value.isInDustbin)
                })
              }
            })
          }//end of if

          this.tableData = taskList;
        })//end of then
        .catch((err) => {
          console.log(err);
        })
    },//end of method

    timeTrans (time, type) {
      if(time == null || time == '')
        return null;
      let date = new Date(new Date(time).getTime() + 8 * 3600 * 1000)
      date = date.toJSON();
      if (type === 1) {
        date = date.substring(0, 10)
      }
      else {
        date = date.substring(0, 19).replace('T', ' ')
      }
      return date
    }
  },
  mounted: function () {
    this.getFrontendTaskList();
  }
}

</script>

<style lang="less" scoped>
div {
  div#searchBox {
    width: 20em;
    margin-top: 1em;
  }
}
</style> 