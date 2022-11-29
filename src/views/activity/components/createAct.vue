<template>
  <div>
    <el-dialog
      v-bind="$attrs"
      v-on="$listeners"
      @open="onOpen"
      @close="onClose"
      title="创建新活动"
      :visible.sync="isShow_"
    >
      <el-row :gutter="15">
        <el-form
          ref="create_activity"
          :model="newact_form"
          :rules="newact_rules"
          size="medium"
          label-width="100px"
          label-position="top"
        >
          <el-col :span="24">
            <el-form-item label="活动标题" prop="title_name">
              <el-input
                v-model="newact_form.title_name"
                placeholder="请输入活动标题"
                clearable
                :style="{ width: '100%' }"
              >
              </el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="活动概要" prop="summary">
              <el-input
                v-model="newact_form.summary"
                placeholder="一句话介绍该活动(30字)"
                :maxlength="30"
                clearable
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="开始时间" prop="start_time">
              <el-date-picker
                type="datetime"
                v-model="newact_form.start_time"
                format="yyyy-MM-dd HH:mm:ss"
                value-format="yyyy-MM-dd HH:mm:ss"
                :style="{ width: '100%' }"
                placeholder="请选择开始时间"
                clearable
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="11">
            <el-form-item label="结束时间" prop="end_time">
              <el-date-picker
                type="datetime"
                v-model="newact_form.end_time"
                format="yyyy-MM-dd HH:mm:ss"
                value-format="yyyy-MM-dd HH:mm:ss"
                :style="{ width: '100%' }"
                placeholder="请选择结束时间"
                clearable
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="活动地址" prop="address">
              <el-input
                v-model="newact_form.address"
                placeholder="请输入活动地址"
                clearable
                prefix-icon="el-icon-location-information"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="" prop="limit_capacity">
              <el-radio-group
                v-model="newact_form.limit_capacity"
                size="medium"
              >
                <el-radio
                  v-for="(item, index) in limit_capacityOptions"
                  :key="index"
                  :label="item.value"
                  :disabled="item.disabled"
                  >{{ item.label }}</el-radio
                >
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item
              label-width="-3px"
              label="参与人数上限"
              prop="capacity"
            >
              <el-input-number
                :disabled="newact_form.limit_capacity != '1'"
                v-model="newact_form.capacity"
                :step="1"
                :min="2"
              ></el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="" prop="repeat" required>
              <el-switch
                v-model="newact_form.repeat"
                active-text="自动重复"
                inactive-text="活动只进行一次"
              ></el-switch>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="重复的间隔天数" prop="repeat_interval">
              <el-input-number
                :disabled="!newact_form.repeat"
                v-model="newact_form.repeat_interval"
                placeholder="重复的间隔天数"
                :step="1"
              >
              </el-input-number>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="" prop="is_private">
              <el-radio-group v-model="newact_form.is_private" size="medium">
                <el-radio-button
                  v-for="(item, index) in privateOptions"
                  :key="index"
                  :label="item.value"
                  :disabled="item.disabled"
                  >{{ item.label }}</el-radio-button
                >
              </el-radio-group>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="详细说明" prop="intro_text">
              <el-input
                v-model="newact_form.intro_text"
                type="textarea"
                placeholder="请输入活动的详细说明"
                :autosize="{ minRows: 4, maxRows: 10 }"
                :style="{ width: '100%' }"
              ></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="24">
            <el-form-item label="上传相关图片" prop="file_value">
              <el-upload
                ref="file_value"
                :file-list="file_valuefileList"
                :action="file_valueAction"
                multiple
                :before-upload="file_valueBeforeUpload"
                list-type="picture-card"
                accept="image/*"
              >
                <i class="el-icon-plus"></i>
                <div slot="tip" class="el-upload__tip">
                  只能上传不超过 2MB 的image/*文件
                </div>
              </el-upload>
            </el-form-item>
          </el-col>
        </el-form>
      </el-row>
      <div slot="footer">
        <el-button @click="cancel_CreateAct">取消</el-button>
        <el-button type="primary" @click="confirm_CreateAct">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { postAct } from "@/api/activity";
import { parseTime } from "@/utils";
export default {
  inheritAttrs: false,
  name: "CreateActivity",
  components: {},
  props: {
    isShow: {
      type: Boolean,
      required: false,
      default: false,
    },
  },
  data() {
    return {
      /**用户信息 */
      user_id: 1145,
      user_name: "用户名-静态测试",

      isShow_: true,
      newact_form: {
        title_name: undefined,
        summary: undefined,
        start_time: "2022-11-17 00:00:00",
        end_time: undefined,
        address: undefined,
        limit_capacity: 1,
        capacity: 2,
        repeat: false,
        repeat_interval: undefined,
        is_private: 1,
        intro_text: undefined,
        file_value: null,
      },
      newact_rules: {
        title_name: [
          {
            required: true,
            message: "请输入活动标题",
            trigger: "blur",
          },
        ],
        summary: [
          {
            required: true,
            message: "一句话介绍该活动(30字)",
            trigger: "blur",
          },
        ],
        start_time: [
          {
            required: true,
            message: "请选择开始时间",
            trigger: "change",
          },
        ],
        end_time: [],
        address: [
          {
            required: true,
            message: "请输入活动地址",
            trigger: "blur",
          },
        ],
        limit_capacity: [
          {
            required: true,
            message: "不能为空",
            trigger: "change",
          },
        ],
        capacity: [],
        repeat_interval: [],
        is_private: [
          {
            required: true,
            message: "不能为空",
            trigger: "change",
          },
        ],
        intro_text: [],
      },
      file_valueAction: "https://jsonplaceholder.typicode.com/posts/",
      file_valuefileList: [],
      limit_capacityOptions: [
        {
          label: "限制人数",
          value: 1,
        },
        {
          label: "不限人数",
          value: 2,
        },
      ],
      privateOptions: [
        {
          label: "公开",
          value: 1,
        },
        {
          label: "私密",
          value: 2,
        },
      ],
    };
  },
  computed: {},
  watch: {
    isShow: {
      handler(newValue, oldValue) {
        this.isShow_ = newValue;
      },
      deep: true,
      immediate: true,
    },
  },
  created() {},
  mounted() {
    this.isShow_ = this.isShow;
    // console.log(this.isShow_);
    // console.log("CreateActivity");
  },
  methods: {
    onOpen() {},
    onClose() {
      //   this.$refs["create_activity"].resetFields();
      this.$emit("CreateActivityClose", this.isShow_);
    },
    cancel_CreateAct() {
      //   this.$emit("update:visible", false);
    },
    confirm_CreateAct() {
      this.$refs["create_activity"].validate(async (valid) => {
        if (!valid) {
          return;
        } else {
          //弹窗确认
          const h = this.$createElement;
          this.$msgbox({
            title: "确定发布活动？",
            // message:
            // h("p", null, [
            //   h("span", null, "将把管理员 "),
            //   h("i", { style: "color: teal" }, mem.name),
            //   h("span", null, " 降级为一般成员"),
            // ]),
            // dangerouslyUseHTMLString: true,
            showCancelButton: true,
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            beforeClose: async (action, instance, done) => {
              //点击了 确认
              if (action === "confirm") {
                instance.confirmButtonLoading = true;
                instance.confirmButtonText = "执行中...";
                //向后端接口请求

                //api
                //请求新增活动
                //组装请求数据包
                console.log(
                  "this.newact_form.start_time",
                  this.newact_form.start_time
                );
                //后端需要格式字符串
                //2022-11-17 00:00:00
                let now = new Date();
                let create_time = now
                  .toLocaleString("zh-CN")
                  .replace(/[/]/, "-");
                create_time = create_time.replace(/[/]/, "-");

                // console.log("create_time", create_time);
                // let create_time=now.getFullYear()+"-"+(now.getMonth()+1)+"-"+now.getDay()+" "+
                let post_data = {
                  // activity_id: undefined,
                  title_name: this.newact_form.title_name,
                  detail_text: this.newact_form.intro_text,
                  summary: this.newact_form.summary,
                  start_time: this.newact_form.start_time,
                  end_time: this.newact_form.end_time,
                  create_time: create_time,
                  address: this.newact_form.address,
                  city: "city_default",
                  hit_num: 0,
                  //limit_capacity 1限制人数
                  limit_capacity: this.newact_form.limit_capacity,
                  capacity: this.newact_form.capacity,
                  current_people: 1,
                  state: 0,
                  participant_num:1,
                  applicant_num:0,

                  //前端repeat_interval undefined，后端null空，返回实体不含前端repeat_interval
                  repeat_interval: this.newact_form.repeat
                    ? this.newact_form.repeat_interval
                    : undefined,
                  creator_id: this.user_id,
                  is_private: this.newact_form.is_private,
                  vote_id: undefined,
                };
                console.log("post_data", post_data);
                await postAct(post_data)
                  .then((res) => {
                    console.log("postAct,res", res);
                    this.$message({
                      type: "success",
                      message: "创建活动成功!",
                    });
                    this.isShow_ = false;
                    this.onClose();
                  })
                  .catch((err) => {
                    console.log(err);
                  })
                  .finally(() => {
                    instance.confirmButtonLoading = false;
                    done();
                  });
              } else {
                done();
              }
            },
          }).then((action) => {
            // this.$message({
            //   type: "info",
            //   message: "action: " + action,
            // });
          });
        }
      });
    },
    file_valueBeforeUpload(file) {
      let isRightSize = file.size / 1024 / 1024 < 2;
      if (!isRightSize) {
        this.$message.error("文件大小超过 2MB");
      }
      let isAccept = new RegExp("image/*").test(file.type);
      if (!isAccept) {
        this.$message.error("应该选择image/*类型的文件");
      }
      return isRightSize && isAccept;
    },
  },
};
</script>
<style>
.el-upload__tip {
  line-height: 1.2;
}
</style>
