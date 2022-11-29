<template>
  <el-container style=" border: 1px solid #eee">

    <el-container>
      <el-main>
        <div style="display:inline-block">
          <el-col  style="width:800px;padding-left: 20px">
            <el-card class="box-card" style="width:100%">
              <div slot="header" class="clearfix" style="text-align:center">
                <span>全部活动</span>

                <el-input ref="searchInput" v-model="search" style="width: 200px; padding-right: 20px;" placeholder="请输入内容" prefix-icon="el-icon-search" />
                <el-button style="margin-right: 20px;" @click="submitFun();open()">搜索</el-button>
                <el-button type="text" @click="dialogFormVisible = true">筛选</el-button>

                <el-dialog title="筛选" :visible.sync="dialogFormVisible">
                  <el-form :model="form" style="text-align:left">
                    <el-form-item label="活动标签" :label-width="formLabelWidth">
                      <el-checkbox-group v-model="checkboxGroup1">
                        <el-checkbox-button v-for="tag in tags" :key="tag" :label="tag">{{ tag.name }}</el-checkbox-button>
                      </el-checkbox-group>
                    </el-form-item>
                    <el-form-item label="活动城市" :label-width="formLabelWidth">
                      <el-select v-model="region" placeholder="请选择活动城市">
                        <el-option label="无" value="" />
                        <el-option label="区域一" value="区域一" />
                        <el-option label="区域二" value="区域二" />
                      </el-select>
                    </el-form-item>
                    <el-form-item label="活动日期" :label-width="formLabelWidth">
                      <el-date-picker v-model="date" type="date" placeholder="选择日期" />
                    </el-form-item>
                  </el-form>
                  <div style="padding-left:50px">已选择：
                    <el-tag v-if="region!=''">{{ region }}</el-tag>
                    <el-tag v-if="date">{{ date.toLocaleDateString().replace(/[/]+/g,'-') }}</el-tag>
                    <el-tag v-for="tag in checkboxGroup1" :key="tag" :label="tag">{{ tag.name }}</el-tag>
                  </div>
                  <div slot="footer" class="dialog-footer">
                    <el-button @click="dialogFormVisible = false">取 消</el-button>
                    <el-button type="primary" @click="dialogFormVisible = false;selectRegion();open()">确 定</el-button>
                  </div>
                </el-dialog>

              </div>

              <div v-for="(list,index) in showData" :key="index">
                <div style="display:inline-block;width:800px">
                  <el-image v-if="list.showimage.length" style="width: 100px; height: 100px; float: right;margin-right: 100px;" :src="list.showimage" fit="cover" />
                  <div style="display:inline-block">
                    <p>活动名称：{{ list.name }} <el-button style="float: right; padding: 3px 0" type="text">点击查看</el-button>
                    </p>
                    <p>活动时间：{{ list.date }} {{ list.start_time }}-{{ list.end_time }}</p>
                    <div v-for="(tag,indexOfTag) in list.tags" :key="indexOfTag" style="display:inline-block">
                      <el-tag v-if="tag.value" style="margin-left: 5px;margin-right: 5px">{{ tags[tag.id].name }}</el-tag>
                    </div>
                  </div>
                </div>
              </div>
            </el-card>
          </el-col>
          <el-col style="width:500px;padding-left: 50px">
            <el-card class="box-card" style="width:100%">

              <div slot="header" class="clearfix" style="text-align:center;">
                <span>热门活动</span>
              </div>

              <el-table :data="tableData" style="width: 100%" :default-sort="{prop: 'heat', order: 'descending'}">
                <el-table-column prop="name" label="名称" :formatter="formatter" />
                <el-table-column label="标签">
                  <template slot-scope="scope">
                    <div v-for="(tag,indexOfTag) in scope.row.tags" :key="indexOfTag" style="display:inline-block">
                      <el-tag v-if="tag.value" style="margin-left: 5px;margin-right: 5px">{{ tags[tag.id].name }}</el-tag>
                    </div>
                  </template>
                </el-table-column>
                <el-table-column prop="heat" label="热度" />
                <el-table-column label="操作">
                  <el-button type="text" size="small">查看</el-button>
                </el-table-column>
              </el-table>
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
    const tagOptions = [
      { tagID: 0, name: '学习' },
      { tagID: 1, name: '工作' },
      { tagID: 2, name: '户外' },
      { tagID: 3, name: '室内' }
    ]
    return {
      tableData: [
        {
          date: '2022-11-22',
          name: '团建1',
          start_time: '08:00',
          end_time: '10:00',
          heat: 500,
          showimage:
            'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Finews.gtimg.com%2Fnewsapp_bt%2F0%2F12146952505%2F1000&refer=http%3A%2F%2Finews.gtimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1671888649&t=ab32917f8d85aca12c015e6fb7757986',
          tags: [
            { id: 0, value: 1 },
            { id: 1, value: 1 },
            { id: 2, value: 1 },
            { id: 3, value: 1 }
          ],
          location: '区域一'
        },
        {
          date: '2022-12-22',
          name: '学习2',
          start_time: '08:00',
          end_time: '10:00',
          heat: 200,
          showimage: '',
          tags: [
            { id: 0, value: 1 },
            { id: 1, value: 0 },
            { id: 2, value: 1 },
            { id: 3, value: 1 }
          ],
          location: '区域一'
        },
        {
          date: '2022-10-12',
          name: '学习1',
          start_time: '08:00',
          end_time: '10:00',
          heat: 600,
          showimage: '',
          tags: [
            { id: 0, value: 1 },
            { id: 1, value: 0 },
            { id: 2, value: 0 },
            { id: 3, value: 0 }
          ],
          location: '区域一'
        },
        {
          date: '2022-11-23',
          name: '团建2',
          start_time: '08:00',
          end_time: '10:00',
          heat: 800,
          showimage: '',
          tags: [
            { id: 0, value: 0 },
            { id: 1, value: 0 },
            { id: 2, value: 0 },
            { id: 3, value: 0 }
          ],
          location: '区域二'
        }
      ],
      showData: '',
      search: '请输入活动名称',
      searchData: '',
      searchResult: true,
      dialogFormVisible: false,

      tags: tagOptions,
      checkboxGroup1: [],
      region: '',
      date: '',
      formLabelWidth: '120px'
    }
  },
  created: function() {
    this.inintData()
  },
  methods: {
    open() {
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
        this.showData = this.searchData
        this.$alert(
          '共找到' + this.searchData.length + '条结果：',
          '查询结果',
          {
            confirmButtonText: '点击查看'
          }
        )
      }
    },
    inintData() {
      this.showData = this.tableData
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
    },
    selectRegion() {
      const search = this.region
      this.searchData = this.tableData.filter(function(tableData) {
        console.log('过滤', tableData)
        const searchField = {
          location: tableData.location
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
