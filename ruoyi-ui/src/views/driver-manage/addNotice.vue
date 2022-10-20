<template>
  <div>
    <div
      style="
        width: 98%;
        margin: 0 auto;
        margin: 20px 10px 0 0;
        overflow: hidden;
      "
    >
      <el-button
        type="primary"
        style="float: right; margin-right: 10px"
        @click="saveClick()"
        >保存</el-button
      >
      <!-- <router-link :to="{ path: '/noticeManage', name: 'noticeManage' }"> -->
      <el-button
        type="primary"
        @click="backClick"
        style="float: right; margin-right: 10px"
        >返回</el-button
      >
      <!-- </router-link> -->
    </div>
    <div class="wrap" style="margin-bottom: 20px">
      <div class="text">创建</div>
      <div class="line"></div>
    </div>
    <el-form
      ref="form"
      :model="form"
      label-width="120px"
      style="width: 90%; margin: 0 auto; margin-top: 20px; margin-bottom: 40px"
    >
      <el-form-item
        label="标题："
        prop="title"
        :rules="[{ required: true, message: '标题为必填项' }]"
      >
        <el-input v-model="form.title"></el-input>
      </el-form-item>
      <el-form-item label="公告截至时间:" prop="finshTime">
        <el-date-picker
          v-model="form.deadline"
          format="yyyy-MM-dd"
          value-format="yyyy-MM-dd"
          :style="{ width: '100%' }"
          placeholder="请选择公告截至时间:"
          clearable
        ></el-date-picker>
      </el-form-item>
      <el-form-item
        label="通知人员："
        :rules="[{ required: true, message: '通知人员为必选项' }]"
      >

        <div :style="{ width: '100%', display: 'flex', flexWrap: 'wrap' }">
          <div v-for="(item, index) in checkUsers" :key="index">
            <el-tag v-if="item" style="margin-right: 20px">{{ item }}</el-tag>
          </div>
        </div>
        <div :style="{ width: '100%', display: 'flex', flexWrap: 'wrap' }">
          <div v-for="(item, index) in checkGroups" :key="index">
            <el-tag v-if="item" style="margin-right: 20px">{{ item }}</el-tag>
          </div>
        </div>

        <div>
          <el-button
            type="primary"
            style="float: right; margin-right: 10px"
            @click="selectGroupClick()"
          >选择群组</el-button
          >
          <el-button
            @click="routeClick"
            type="primary"
            style="float: right; margin-right: 10px"
          >选择参与人员</el-button
          >
        </div>
        <!-- </router-link> -->
      </el-form-item>
      <el-form-item
        label="公告内容："
        prop="content"
        :rules="[{ required: true, message: '公告内容为必填项' }]"
      >
        <quill-editor
          class="editor"
          v-model="form.content"
          ref="myQuillEditor"
          :options="editorOption"
          @blur="onEditorBlur($event)"
          @focus="onEditorFocus($event)"
          @change="onEditorChange($event)"
        >
        </quill-editor>
      </el-form-item>
    </el-form>
    <!-- 选择群组弹窗 -->
    <el-dialog
      :title="diaTitle"
      :visible.sync="dialogVisible"
      width="600px"
      :before-close="handleClose"
    >
      <el-table
        ref="multipleTable"
        border
        :data="tableData"
        tooltip-effect="dark"
        style="width: 100%"
        @selection-change="handleSelectionChange"
      >
        <el-table-column type="selection" align="center"></el-table-column>
        <el-table-column label="群组名称" align="center">
          <template slot-scope="scope">{{ scope.row.name }}</template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="trueClick()">确定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
// 工具栏配置
const toolbarOptions = [
  ["bold", "italic", "underline", "strike"], // 加粗 斜体 下划线 删除线
  ["blockquote", "code-block"], // 引用  代码块
  [{ header: 1 }, { header: 2 }], // 1、2 级标题
  [{ list: "ordered" }, { list: "bullet" }], // 有序、无序列表
  [{ script: "sub" }, { script: "super" }], // 上标/下标
  [{ indent: "-1" }, { indent: "+1" }], // 缩进
  // [{'direction': 'rtl'}],                         // 文本方向
  [{ size: ["small", false, "large", "huge"] }], // 字体大小
  [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
  [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
  [{ font: [] }], // 字体种类
  [{ align: [] }], // 对齐方式
  ["clean"], // 清除文本格式
  ["link", "image", "video"], // 链接、图片、视频
];

import { selectGroupList, noticeAdd } from "@/api/driver/noticeManage";
import { quillEditor } from "vue-quill-editor";

import "quill/dist/quill.core.css";
import "quill/dist/quill.snow.css";
import "quill/dist/quill.bubble.css";

export default {
  name: "addNotice",
  data() {
    return {
      form: {
        title: "",
        content: "",
        groupId: [],
        driverId: [],
        deadline:undefined
      },
      tableData: [],
      checkUsers: [],
      checkGroups:[],
      checkGroupsId:[],
      // 选择群组弹窗
      dialogVisible: false,
      diaTitle: "选择群组",
      content: this.value,
      quillUpdateImg: false, // 根据图片上传状态来确定是否显示loading动画，刚开始是false,不显示
      editorOption: {
        theme: "snow", // or 'bubble'
        placeholder: "公告内容",
        modules: {
          toolbar: {
            container: toolbarOptions,
            handlers: {
              image: function (value) {
                if (value) {
                  // 触发input框选择图片文件
                  document.querySelector(".avatar-uploader input").click();
                } else {
                  this.quill.format("image", false);
                }
              },
            },
          },
        },
      },
      serverUrl: process.env.BASE_API + "upload", // 这里写你要上传的图片服务器地址
      header: {
        // token: sessionStorage.token
      }, // 有的图片服务器要求请求头需要有token
    };
  },
  props: {
    /*编辑器的内容*/
    value: {
      type: String,
    },
    /*图片大小*/
    maxSize: {
      type: Number,
      default: 4000, //kb
    },
  },
  components: {
    quillEditor,
  },
  created() {},
  mounted() {
    if (localStorage.getItem("formNotice")) {
      this.form = JSON.parse(localStorage.getItem("formNotice"));
    }

    if (localStorage.getItem("myAddNoticeDataNameList")) {
      this.checkUsers = localStorage.getItem("myAddNoticeDataNameList").split(",");
      console.log("checkUsers",this.checkUsers)
    }

    if (localStorage.getItem("checkGroups")) {
      this.checkGroups = localStorage.getItem("checkGroups").split(",");
    }
    if (localStorage.getItem("checkGroupsId")) {
      this.checkGroupsId = localStorage.getItem("checkGroupsId").split(",");
    }

    this.getParams();
  },
  methods: {
    routeClick() {
      this.$router.push("/addSelectParticipants");
      localStorage.setItem("formNotice", JSON.stringify(this.form));

      localStorage.setItem("checkGroups", this.checkGroups);
      localStorage.setItem("checkGroupsId", this.checkGroupsId);
    },
    backClick() {
      localStorage.removeItem("myAddNoticeDataList");
      localStorage.removeItem("myAddNoticeDataNameList");
      localStorage.removeItem("checkGroups");
      localStorage.removeItem("checkGroupsId");
      localStorage.removeItem("formNotice");
      (this.form = {
        title: "",
        content: "",
        groupId: "",
        driverId: "",
        deadline:undefined,
      }),
        this.$router.push("/driver/noticeManage");
    },
    // 新增编辑弹窗关闭
    handleClose() {
      this.toggleSelection();
      this.dialogVisible = false;

      if(this.checkGroupsId){
        for(let i=0;i<this.tableData.length;i++){
          for(let j=0;j<this.checkGroupsId.length;j++){
            if(this.checkGroupsId[j] == this.tableData[i].id){
              this.$refs.multipleTable.toggleRowSelection(this.tableData[i],true);
            }
          }
        }
      }
    },
    // table取消勾选
    toggleSelection(rows) {
      if (rows) {
        rows.forEach((row) => {
          this.$refs.multipleTable.toggleRowSelection(row);
        });
      } else {
        this.$refs.multipleTable.clearSelection();
      }
    },

    //table打勾
    handleSelectionChange(val) {
    },
    // 点击选择群组按钮
    selectGroupClick() {
      console.log("this.checkGroups111",this.checkGroupsId)
      this.dialogVisible = true;
      selectGroupList(this.form)
        .then((response) => {
          console.log(response);
          this.tableData = response.rows;
        })
        .catch((err) => {
          console.log(err);
        }).then(()=>{
        if(this.checkGroupsId){
          for(let i=0;i<this.tableData.length;i++){
            for(let j=0;j<this.checkGroupsId.length;j++){
              if(this.checkGroupsId[j] == this.tableData[i].id){
                this.$refs.multipleTable.toggleRowSelection(this.tableData[i],true);
              }
            }
          }
        }
      });
    },
    onEditorBlur() {
      //失去焦点事件
    },
    onEditorFocus() {
      //获得焦点事件
    },
    onEditorChange() {
      //内容改变事件
      this.$emit("input", this.content);
    },

    // 富文本图片上传前
    beforeUpload() {
      // 显示loading动画
      this.quillUpdateImg = true;
    },
    uploadSuccess(res, file) {
      // res为图片服务器返回的数据
      // 获取富文本组件实例
      let quill = this.$refs.myQuillEditor.quill;
      // 如果上传成功
      if (res.code == 200) {
        // 获取光标所在位置
        let length = quill.getSelection().index;
        // 插入图片  res.url为服务器返回的图片地址
        quill.insertEmbed(length, "image", res.data.url);
        // 调整光标到最后
        quill.setSelection(length + 1);
      } else {
        this.$message.error("图片插入失败");
      }
      // loading动画消失
      this.quillUpdateImg = false;
    },
    // 富文本图片上传失败
    uploadError() {
      // loading动画消失
      this.quillUpdateImg = false;
      this.$message.error("图片插入失败");
    },
    // 获取数据
    getParams() {
      if (localStorage.getItem("myAddNoticeDataList")) {
        this.form.driverId = localStorage
          .getItem("myAddNoticeDataList")
          .split(",");
      } else {
        this.form.driverId = [];
      }
    },
    // 点击提交按钮
    saveClick() {
      noticeAdd(this.form)
        .then((response) => {
          console.log(response);
          this.$message({
            message: "保存成功",
            type: "success",
          });
          setTimeout(() => {
            this.backClick();
          }, 1000);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 点击确定按钮
    trueClick() {
      this.$message({
        message: "选择群组成功",
        type: "success",
      });
      this.dialogVisible = false;
      let arr = [];
      let arrName = [];
      const _selectData = this.$refs.multipleTable.selection
      if(_selectData.length > 0){
        for(const item of _selectData){
          arr.push(item.id);
          arrName.push(item.name);
        }
      } else {
        this.checkGroupsId = [];
      }
      this.form.groupId = arr;
      this.checkGroups = arrName;
      for (let j=0;j<arr.length;j++){
        this.checkGroupsId.push(arr[j])
      }
    },
  },
  watch: {
    $route: "getParams",
  },
};
</script>

<style scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
}
.text {
  width: 10%;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  float: left;
  font-weight: 400;
}
.line {
  width: 90%;
  border-bottom: 3px solid #333399;
  position: absolute;
  left: 10%;
  top: 50%;
  z-index: 1;
  float: left;
}
.editor {
  line-height: normal !important;
}
.ql-snow .ql-tooltip[data-mode="link"]::before {
  content: "请输入链接地址:";
}
.ql-snow .ql-tooltip.ql-editing a.ql-action::after {
  border-right: 0px;
  content: "保存";
  padding-right: 0px;
}

.ql-snow .ql-tooltip[data-mode="video"]::before {
  content: "请输入视频地址:";
}

.ql-snow .ql-picker.ql-size .ql-picker-label::before,
.ql-snow .ql-picker.ql-size .ql-picker-item::before {
  content: "14px";
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="small"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="small"]::before {
  content: "10px";
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="large"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="large"]::before {
  content: "18px";
}
.ql-snow .ql-picker.ql-size .ql-picker-label[data-value="huge"]::before,
.ql-snow .ql-picker.ql-size .ql-picker-item[data-value="huge"]::before {
  content: "32px";
}

.ql-snow .ql-picker.ql-header .ql-picker-label::before,
.ql-snow .ql-picker.ql-header .ql-picker-item::before {
  content: "文本";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="1"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="1"]::before {
  content: "标题1";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="2"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="2"]::before {
  content: "标题2";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="3"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="3"]::before {
  content: "标题3";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="4"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="4"]::before {
  content: "标题4";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="5"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="5"]::before {
  content: "标题5";
}
.ql-snow .ql-picker.ql-header .ql-picker-label[data-value="6"]::before,
.ql-snow .ql-picker.ql-header .ql-picker-item[data-value="6"]::before {
  content: "标题6";
}

.ql-snow .ql-picker.ql-font .ql-picker-label::before,
.ql-snow .ql-picker.ql-font .ql-picker-item::before {
  content: "标准字体";
}
.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="serif"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="serif"]::before {
  content: "衬线字体";
}
.ql-snow .ql-picker.ql-font .ql-picker-label[data-value="monospace"]::before,
.ql-snow .ql-picker.ql-font .ql-picker-item[data-value="monospace"]::before {
  content: "等宽字体";
}
</style>

