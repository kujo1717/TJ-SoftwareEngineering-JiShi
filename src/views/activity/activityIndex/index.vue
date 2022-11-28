<template>
  <el-container style=" border: 1px solid #eee">

    <el-container>
      <!--查询结果展示-->
      <el-main>
        <el-card v-if="tableData.length!=searchData.length&&searchData.length&&searchResult" class="box-card" style="margin-left: 50px;margin-right: 50px;margin-bottom: 50px;">
          <div slot="header" class="clearfix">
            <span>查询结果</span>
            <el-button icon="el-icon-close" circle style="position: absolute;right:100px;top:29px;" @click="searchResult = 0" />
          </div>
          <div v-for="(list,index) in searchData" :key="index" style="height: 50px">
            活动名称：{{ list.name }} 活动日期：{{ list.date }} 活动时间：{{ list.start_time }}-{{ list.end_time }}
            <el-button style="float: right; padding: 3px 0" type="text">点击查看</el-button>
          </div>
        </el-card>
        <!--标签活动展示-->
        <el-card v-if="selectByTags" class="box-card" style="margin-left: 50px;margin-right: 50px;margin-bottom: 50px;">
          <div slot="header" class="clearfix">
            <span>{{ tableData[0].tags[selectTag].name }} 标签下所有活动</span>
            <el-button icon="el-icon-close" circle style="position: absolute;right:100px;top:29px;" @click="selectByTags=0" />
          </div>
          <div v-for="(list,index) in tableData" :key="index">
            <div v-if="list.tags[selectTag].value" style="height: 50px">
              活动名称：{{ list.name }} 活动日期：{{ list.date }} 活动时间：{{ list.start_time }}-{{ list.end_time }}
              <el-button style="float: right; padding: 3px 0" type="text">点击查看</el-button>
            </div>
          </div>
        </el-card>
        <!--全部活动＆最热活动展示-->
        <div style="display:inline-block">
          <el-col style="width:607px;padding-left: 50px;padding-right: 50px;">
            <el-card class="box-card">
              <div slot="header" class="clearfix" style="text-align:center">
                <span>全部活动</span>
                <el-input v-show="inputSearchInfo" v-model="search" style="margin-left: 20px;width:150px" />
                <i :class="{'el-icon-search': !inputSearchInfo, 'el-icon-close': inputSearchInfo}" style="padding-left:10px" @click="inputSearchInfo = !inputSearchInfo" />
                <el-button v-show="inputSearchInfo" round style="margin-left: 20px;" @click="submitFun();open();searchResult = 1">搜索</el-button>
              </div>
              <div v-for="(list,index) in tableData" :key="index" style="height: 98px">
                <p>活动名称：{{ list.name }} <el-button style="float: right; padding: 3px 0" type="text">点击查看</el-button>
                </p>
                <p>活动时间：{{ list.date }} {{ list.start_time }}-{{ list.end_time }}</p>
                <div v-for="(tag,indexOfTag) in list.tags" :key="indexOfTag" style="display:inline-block">
                  <el-tag v-if="tag.value" style="margin-left: 5px;margin-right: 5px" @click="selectByTags=1;selectTag=indexOfTag">{{ tag.name }}</el-tag>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col style="width:607px;padding-left: 50px;padding-right: 50px;">
            <el-card class="box-card">
              <div slot="header" class="clearfix" style="text-align:center">
                <span>最热活动</span>

              </div>
              <div v-for="(list,index) in tableData" :key="index" style="height: 98px">
                <p>活动名称：{{ list.name }} <el-button style="float: right; padding: 3px 0" type="text">点击查看</el-button>
                </p>
                <p>活动时间：{{ list.date }} {{ list.start_time }}-{{ list.end_time }}</p>
                <div v-for="(tag,indexOfTag) in list.tags" :key="indexOfTag" style="display:inline-block">
                  <el-tag v-if="tag.value" style="margin-left: 5px;margin-right: 5px">{{ tag.name }}</el-tag>
                </div>
              </div>
            </el-card>
          </el-col>
        </div>

      </el-main>
    </el-container>
  </el-container>
</template>
<style>
.el-header {
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
<script src="./vue.js"></script>
<script>
export default {
  data() {
    return {
      tableData: [
        {
          date: '2022-10-22 11:59',
          name: '团建1',
          start_time: '08:00',
          end_time: '10:00',
          heat: 500,
          tags: [
            { value: 1, name: '户外' },
            { value: 0, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '学习2',
          start_time: '08:00',
          end_time: '10:00',
          heat: 200,
          tags: [
            { value: 1, name: '户外' },
            { value: 1, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '学习1',
          start_time: '08:00',
          end_time: '10:00',
          heat: 600,
          tags: [
            { value: 1, name: '户外' },
            { value: 1, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '团建2',
          start_time: '08:00',
          end_time: '10:00',
          heat: 800,
          tags: [
            { value: 0, name: '户外' },
            { value: 0, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '团建1',
          start_time: '08:00',
          end_time: '10:00',
          heat: 500,
          tags: [
            { value: 1, name: '户外' },
            { value: 0, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '学习2',
          start_time: '08:00',
          end_time: '10:00',
          heat: 200,
          tags: [
            { value: 1, name: '户外' },
            { value: 1, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '学习1',
          start_time: '08:00',
          end_time: '10:00',
          heat: 600,
          tags: [
            { value: 1, name: '户外' },
            { value: 1, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '团建2',
          start_time: '08:00',
          end_time: '10:00',
          heat: 800,
          tags: [
            { value: 0, name: '户外' },
            { value: 0, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '团建1',
          start_time: '08:00',
          end_time: '10:00',
          heat: 500,
          tags: [
            { value: 1, name: '户外' },
            { value: 0, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '学习2',
          start_time: '08:00',
          end_time: '10:00',
          heat: 200,
          tags: [
            { value: 1, name: '户外' },
            { value: 1, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '学习1',
          start_time: '08:00',
          end_time: '10:00',
          heat: 600,
          tags: [
            { value: 1, name: '户外' },
            { value: 1, name: '学习' }
          ]
        },
        {
          date: '2022-10-22 11:59',
          name: '团建2',
          start_time: '08:00',
          end_time: '10:00',
          heat: 800,
          tags: [
            { value: 0, name: '户外' },
            { value: 0, name: '学习' }
          ]
        }
      ],
      search: '请输入活动名称',
      searchData: '', // 搜索数据
      selectTag: 0, // 查看的标签的index
      inputSearchInfo: false, // 是否输入
      searchResult: true, // 是否展示搜索结果
      selectByTags: false // 是否展示标签活动
    }
  },
  created: function() {
    this.inintData()
  },
  methods: {
    open() {
      if (this.search.length != 0) {
        if (this.searchData.length == 0) {
          this.$alert('未找到相关活动数据！', '查询结果', {
            confirmButtonText: '确定',
            callback: (action) => {
              this.$message({
                type: 'info',
                message: `action: ${action}`
              })
            }
          })
        } else {
          this.$alert(
            '共找到' + this.searchData.length + '条结果：',
            '查询结果',
            {
              confirmButtonText: '点击查看'
            }
          )
        }
      }
    },
    inintData() {
      this.searchData = this.tableData
    },
    submitFun() {
      const search = this.search
      this.searchData = this.tableData.filter(function(tableData) {
        console.log('过滤', tableData)
        const searchField = {
          name: tableData.name
        }
        return Object.keys(searchField).some(function(key) {
          console.log('key值', key)
          return String(tableData[key]).toLowerCase().indexOf(search) > -1
        })
      })
    }
  }
}
</script>
