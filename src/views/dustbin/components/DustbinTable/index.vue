<template>
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
</template>

<script>
export default {
  name: DustbinTable,
  props: ['tableData'],
  data () {
    return {

    }
  },
  methods: {
    /**
 * 分页所用函数start
 */
    searchResource () {
      this.loading = true;
      //函数返回值为筛选后的列表
      const search = this.input;
      let result = this.order_table.slice(0);//深拷贝
      let i = 0;
      if (search)//有内容才执行关键字筛选
      {
        result = result.filter(data => {
          return Object.keys(data).some(key => {
            return String(data[key]).toLowerCase().indexOf(search) > -1
          })
        })

      }
      //复选框筛选
      for (i = 0; i < result.length; i++) {
        console.log(result.length);
        console.log(i);

        if ((result[i].role == "老人" && !this.bool_showElder) ||
          (result[i].role == "护工" && !this.bool_showCarer) ||
          (result[i].role == "医生" && !this.bool_showDoctor) ||
          (result[i].haveBad == "无" && this.bool_showBadOnly)) {
          //删除不符合条件的项
          result.splice(i, 1);
          i--;//！！！！注意result.length动态变化！
          continue;
        }
      }

      //分页参数的更改
      this.query.total = result.length;//总页数设置
      this.loading = false;
      return result;
    },
    //切换当前页显示的数据条数，执行方法
    handleSizeChange (val) {
      //console.log(`每页 ${val} 条`);
      this.query.pageSize = val;
      this.getPageData();
    },
    //切换页数，执行方法
    handleCurrentChange (val) {
      //console.log(`当前页: ${val}`);
      this.query.pageNum = val;
      this.getPageData();
    },
    getPageData () {
      this.postKeyAndFetch();
      const DataAll = this.show_table.slice(0);//深拷贝
      //每次执行方法，将展示的数据清空
      this.show_table = [];
      //第一步：当前页的第一条数据在总数据中的位置
      let strlength = (this.query.pageNum - 1) * this.query.pageSize + 1;
      //第二步：当前页的最后一条数据在总数据中的位置
      let endlength = this.query.pageNum * this.query.pageSize;
      //第三步：此判断很重要，执行时机：当分页的页数在最后一页时，进行重新筛选获取数据时
      //获取本次表格最后一页第一条数据所在的位置的长度
      let resStrLength = 0;
      if (DataAll.length % this.query.pageSize == 0) {
        resStrLength = (parseInt(DataAll.length / this.query.pageSize) - 1) * this.query.pageSize + 1
      } else {
        resStrLength = parseInt(DataAll.length / this.query.pageSize) * this.query.pageSize + 1
      }
      //如果上一次表格的最后一页第一条数据所在的位置的长度 大于 本次表格最后一页第一条数据所在的位置的长度，则将本次表格的最后一页第一条数据所在的位置的长度 为最后长度
      if (strlength > resStrLength) {
        strlength = resStrLength
      }
      //第四步：此判断很重要，当分页的页数切换至最后一页，如果最后一页获取到的数据长度不足最后一页设置的长度，则将设置的长度 以 获取到的数据长度 为最后长度
      if (endlength > DataAll.length) {
        endlength = DataAll.length;
      }
      //第五步：循环获取当前页数的数据，放入展示的数组中
      for (let i = strlength - 1; i < endlength; i++) {
        this.show_table.push(DataAll[i]);
      }
      //数据的总条数
      this.query.total = DataAll.length;
    },
    /**
     * 分页所用函数end
     */
  }
}
</script>

<style>
</style> */