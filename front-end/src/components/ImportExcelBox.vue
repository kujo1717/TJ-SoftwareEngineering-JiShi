/**
导入excel表格的组件
@author: hym
@editor: hym
@lastUpdate hym 10.26
*/
<template>
  <div>
    <el-upload
      class="upload-demo"
      action="https://jsonplaceholder.typicode.com/posts/"
      :on-remove="handleRemove"
      :on-change="onUploadChange"
      :on-preview="handlePictureCardPreview"
      :limit="1"
      :file-list="fileList"
    >
      <el-button
        size="small"
        type="primary"
      >点击上传</el-button>
    </el-upload>
  </div>
</template>

<script>
export default {
  name: 'ImportExcelBox',
  data () {
    return {
      // 查看放大图的url
      dialogImageUrl: '',
      // 上传文件数据
      file_data: null,
      // 从后端获取的图片url
      image_url: null,
      // 是否展示获取的图片
      image_show: false,
      // 当前上传的图片数量
      picture_amount: 0,
      // 上传的文件列表
      fileList: [],
    }
  },
  methods: {
    // 文件状态改变时的钩子，添加文件、上传成功和上传失败时都会被调用
    onUploadChange (file, fileList) {
      console.log(file);
      this.fileList = fileList;
      this.file_data = file;
      this.picture_amount += 1;
      const isExcel = (file.raw.type === 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet');
      const isLt1M = file.size / 1024 / 1024 * 10 < 1;

      if (!isExcel) {
        this.$message.error('上传文件只能是excel格式!');
        this.picture_amount -= 1;
        // 从文件列表中删除最后一个元素
        this.fileList.pop();
        return false;
      }
      if (!isLt1M) {
        this.$message.error('上传文件大小不能超过 10MB!');
        this.picture_amount -= 1;
        // 从文件列表中删除最后一个元素
        this.fileList.pop();
        return false;
      }
    },
    handleRemove (file, fileList) {
      setTimeout(() => {
        this.fileList = fileList;
      }, 1000);
    },
    handlePictureCardPreview (file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
  }
}
</script>

<style lang="less" scoped>
</style>