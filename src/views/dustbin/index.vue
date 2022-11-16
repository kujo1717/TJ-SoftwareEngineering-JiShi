<template>
  <div>
    <div id="searchBox">
      <el-input
        v-model="search"
        size="mini"
        placeholder="输入关键字搜索"
      />
    </div>
    <el-table
      :data="tableData.filter(data => !search || data.name.toLowerCase().includes(search.toLowerCase()))"
      style="width: 100%"
    >
      <el-table-column
        label="删除日期"
        prop="date"
      >
      </el-table-column>
      <el-table-column
        label="事项名称"
        prop="name"
      >
      </el-table-column>
      <el-table-column
        label="分组"
        prop="classification"
      >
      </el-table-column>
      <el-table-column
        label="原定时间段"
        prop="timeRange"
      >
      </el-table-column>

      <el-table-column align="right">
        <template
          slot="header"
          slot-scope="scope"
        >
          <el-button @click="clearDustbin()">清空回收站</el-button>

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
export default {
  name: 'Dustbin',
  data () {
    return {
      //需要一开始由后端赋值
      tableData: [{
        task_id: 100,
        date: '2016-05-02 12:00:00',
        name: '背单词',
        classification: '学习',
        timeRange:'null'
      }, {
        task_id: 101,
        date: '2016-05-04 12:00:00',
        name: '玩原神',
        classification: '游戏',
        timeRange:'null'
      }, {
        task_id: 102,
        date: '2016-05-01 12:00:00',
        name: '扫地',
        classification: '家务',
        timeRange:'null'
      }, {
        task_id: 103,
        date: '2016-05-03 12:00:00',
        name: '上课',
        classification: '学习',
        timeRange:'null'
      }],
      search: ''
    }
  },
  methods: {
    removeOneFromDustbin (task_id) {
      this.tableData = this.tableData.filter(item => {
        return item.task_id != task_id;
      })
    },
    handleRestore (index, row) {
      console.log(index, row);
      //从前端回收站删除这一条数据
      this.removeOneFromDustbin(row.task_id);

      //请求后端恢复事项
      //code here...

      //如果后端请求成功=>.then
        this.$message({
            type: 'success',
            message: '事项恢复成功'
          });

    },
    handleSmash (index, row) {
      console.log(index, row);
      this.$confirm('您确定要永久删除事项吗？删除的事项不可恢复！', '确认信息', {
        distinguishCancelAndClose: true,
        confirmButtonText: '确定',
        cancelButtonText: '取消'
      })
        .then(() => {
          //从前端回收站删除这一条数据
          this.removeOneFromDustbin(row.task_id);

          //向后端请求删除...
          //code here...

          //如果删除成功
          this.$message({
            type: 'success',
            message: '事项永久删除成功'
          });
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
          //清空前端回收站
          this.tableData = [];

          //向后端请求删除...
          //code here...

          //如果删除成功
          this.$message({
            type: 'success',
            message: '回收站清空成功'
          });
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '取消清空回收站'
          })
        });
    },
  },
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