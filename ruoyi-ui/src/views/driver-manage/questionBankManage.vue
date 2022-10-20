<template>
  <div>
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <el-form-item label="标题：">
        <el-input v-model="listQuery.name" placeholder="标题" />
      </el-form-item>
      <el-form-item label="题型：">
        <el-select v-model="listQuery.type" placeholder="请选择题型">
          <el-option
            v-for="item in status"
            :key="item.value"
            :value="item.value"
            :label="item.label"
          ></el-option>
        </el-select>
      </el-form-item>
      <!-- <el-button type="primary" style="float: right;margin-right: 30px;" @click="handleFilter">查询</el-button>
      <div>
        <el-button
          type="primary"
          style="float: right;margin:0 20px 30px 0;"
          @click="importClick()"
        >导入</el-button>
        <el-button
          type="primary"
          style="float: right;margin:0 20px 20px 0;"
          @click="addClick('1')"
        >新增</el-button>
      </div> -->
      <buttonGroup
        @handleFilter="handleFilter"
        leadingIn
        @handleAdd="addClick('1')"
        @handleImportant="importClick"
        style="float: right"
        :addHasPermi="['person:dExamProblem:add']"
        :importantHasPermi="['person:dExamProblem:import']"
      />
    </el-form>
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column
        type="index"
        :index="typeIndex"
        label="序号"
        align="center"
      ></el-table-column>
      <el-table-column label="题型" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.type === 1">单选题</span>
          <span v-else-if="scope.row.type === 2">多选题</span>
          <span v-else-if="scope.row.type === 3">判断题</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="标题" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | data }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            style="margin-right: 15px"
            @click="addClick('2', scope.row.id)"
            size="small"
            plain
            v-hasPermi="['person:dExamProblem:edit']"
            >编辑</el-button
          >
          <el-button
            type="danger"
            size="small"
            @click="deleteClick(scope.row.id)"
            v-hasPermi="['person:dExamProblem:delete']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.pageNum"
      :limit.sync="listQuery.pageSize"
      @pagination="getList"
    />
    <!-- 新增编辑弹窗-->
    <el-dialog
      :title="diaTitle"
      :visible.sync="dialogVisible"
      width="640px"
      :before-close="handleClose"
    >
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item
          label="选择题型："
          prop="type"
          :rules="[{ required: true, message: '选择题型为必选项' }]"
        >
          <el-select
            v-model="form.type"
            @change="questionChange"
            placeholder="请选择题型"
            style="width: 40%"
          >
            <el-option
              v-for="item in status"
              :key="item.value"
              :value="item.value"
              :label="item.label"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="题目标题："
          prop="name"
          :rules="[{ required: true, message: '题目标题为必填项' }]"
        >
          <el-input
            type="textarea"
            v-model="form.name"
            placeholder="请输入题目标题"
          ></el-input>
        </el-form-item>
        <div style="overflow: hidden">
          <div style="float: left">
            <el-form-item
              v-if="isOption"
              label="答案"
              label-width="60px"
              :rules="[{ required: true, message: '答案为必填项' }]"
            ></el-form-item>
          </div>
          <div v-if="isOption" style="float: left">
            <el-form-item label="A：" prop="optionA">
              <el-input
                v-model="form.optionA"
                placeholder="请输入选项内容"
                style="width: 200%"
              ></el-input>
            </el-form-item>
            <el-form-item label="B：" prop="optionB">
              <el-input
                v-model="form.optionB"
                placeholder="请输入选项内容"
                style="width: 200%"
              ></el-input>
            </el-form-item>
            <el-form-item label="C：" prop="optionC">
              <el-input
                v-model="form.optionC"
                placeholder="请输入选项内容"
                style="width: 200%"
              ></el-input>
            </el-form-item>
            <el-form-item label="D：" prop="optionD">
              <el-input
                v-model="form.optionD"
                placeholder="请输入选项内容"
                style="width: 200%"
              ></el-input>
            </el-form-item>
          </div>
        </div>
        <div class="wrap">
          <div class="text">正确答案</div>
          <div class="line"></div>
        </div>
        <el-form-item label="正确答案：" prop="answer">
          <el-select
            v-if="form.type == 1 || form.type == 3"
            v-model="form.answer"
            placeholder="请选择"
            style="width: 40%"
            :props="{ multiple: true }"
            clearable
          >
            <el-option :label="isOption ? 'A' : '对'" value="A"></el-option>
            <el-option :label="isOption ? 'B' : '错'" value="B"></el-option>
            <el-option v-if="this.isOption" label="C" value="C"></el-option>
            <el-option v-if="this.isOption" label="D" value="D"></el-option>
          </el-select>
          <el-cascader
            v-else
            v-model="answer"
            :options="options"
            :props="{ multiple: true }"
            @change="questionCascader"
            clearable
          ></el-cascader>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button
          v-if="showBtn"
          :loading="btn_loading"
          type="primary"
          @click="handleSaveAdd()"
          >保 存</el-button
        >
        <el-button
          v-else
          :loading="btn_loading"
          type="primary"
          @click="handleSaveEdit()"
          >保 存</el-button
        >
      </span>
    </el-dialog>
    <!-- 导入弹窗-->
    <el-dialog
      :title="diaTitle1"
      :visible.sync="dialogVisible1"
      width="600px"
      :before-close="handleClose1"
    >
      <div style="width: 640px; height: 200px">
        <el-form
          ref="form"
          :model="form"
          label-width="100px"
          style="float: left"
        >
          <el-form-item label="导入文件："></el-form-item>
        </el-form>
        <el-upload
          style="float: left; margin-left: 10px"
          drag
          ref="upload"
          class="upload-btn"
          action="string"
          accept=".xls, .xlsx"
          :before-upload="onBeforeUploadImage"
          :http-request="UploadFile"
          multiple
        >
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">
            将文件拖到此处，或
            <em>点击上传</em>
          </div>
          <div class="el-upload__tip" slot="tip">
            <a href="/template.xlsx" download="题库管理.xlsx">下载模板</a>
          </div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="uploadClick">导入</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  questionBankList,
  questionBankDelete,
  questionBankDetails,
  questionBankEdit,
  questionBankAdd,
  UploadFiles,
} from "@/api/driver/questionBankManage";

import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "questionBankManage",
  components: {
    buttonGroup,
  },
  data() {
    return {
      status: [
        {
          value: null,
          label: "全部",
        },
        {
          value: 1,
          label: "单选题",
        },
        {
          value: 2,
          label: "多选题",
        },
        {
          value: 3,
          label: "判断题",
        },
      ],
      total: 10,
      isOption: true,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: "",
        type: null,
      },
      btn_loading: false,
      listLoading: false,
      tableData: [],
      // 新增编辑弹窗
      dialogVisible: false,
      diaTitle: "题目信息",
      dialogVisible1: false,
      diaTitle1: "导入",
      showBtn: true,
      answer: undefined,
      options: [
        {
          value: "A",
          label: "A",
        },
        {
          value: "B",
          label: "B",
        },
        {
          value: "C",
          label: "C",
        },
        {
          value: "D",
          label: "D",
        },
      ],
      form: {
        type: 1,
        name: "",
        optionA: "",
        optionB: "",
        optionC: "",
        optionD: "",
        answer: null,
      },
    };
  },
  created() {
    this.getList();
  },
  //格式化时间戳
  filters: {
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      const day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      const hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      const min = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      const sec = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds();
      return (
        year + "-" + month + "-" + day + " " + hours + ":" + min + ":" + sec
      );
    },
  },
  methods: {
    questionCascader(val) {
      console.log(val);
    },
    //列表显示序号
    typeIndex(index) {
      return (this.listQuery.pageNum - 1) * this.listQuery.pageSize + index + 1;
    },
    // 查询
    handleFilter() {
      this.listQuery.pageNum = 1;
      this.getList();
    },
    // 新增编辑弹窗关闭
    handleClose() {
      this.btn_loading = false;
      this.dialogVisible = false;
      this.$refs["form"].resetFields();
    },
    // 导入弹窗关闭
    handleClose1() {
      this.dialogVisible1 = false;
    },
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      console.log(file);
    },
    // 单选 多选 判断
    questionChange(val) {
      console.log(val);

      switch (val) {
        case 1: //单选
          this.isOption = true;

          this.form.answer = null;
          break;
        case 2: //多选
          this.isOption = true;
          this.form.answer = null;
          break;
        case 3: //判断
          this.form.answer = null;
          this.form.optionA = undefined;
          this.form.optionB = undefined;
          this.form.optionC = undefined;
          this.form.optionD = undefined;
          this.isOption = false;
          break;
        default:
          break;
      }
    },
    // 获取table的表格数据
    getList() {
      this.listLoading = true;
      questionBankList(this.listQuery)
        .then((response) => {
          console.log(response);
          this.total = response.total;
          this.tableData = response.rows;
          this.listLoading = false;
        })
        .catch((err) => {
          this.listLoading = false;
          console.log(err);
        });
    },
    uploadClick() {
      if (this.upfile) {
        console.log(this.upfile);
        let fd = new FormData();
        fd.append("file", this.upfile); // 要提交给后台的文件
        console.log(fd);
        UploadFiles(fd)
          .then((response) => {
            console.log("提交文件", response);
            this.$message({
              message: "导入成功",
              type: "success",
            });
            this.getList();

            this.dialogVisible1 = false;
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
      }
    },
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
    },
    // 点击新增和编辑按钮判断
    // addClick(data, id) {
    //   if (data === "1") {
    //     this.dialogVisible = true;
    //     this.diaTitle = "题目信息";
    //     this.showBtn = true;
    //   } else if (data === "2") {
    //     this.dialogVisible = true;
    //     this.diaTitle = "题目信息";
    //     this.showBtn = false;
    //     this.handleDetails(id);
    //   }
    // },
    //导入按钮
    importClick() {
      this.dialogVisible1 = true;
    },
    // 编辑获取数据
    // handleDetails(id) {
    //   questionBankDetails(id)
    //     .then((response) => {
    //       console.log(response);
    //       this.form = response.data;
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // },
    // 新增保存
    // handleSaveAdd() {
    //   this.$refs["form"].validate((valid) => {
    //     if (valid) {
    //       debugger
    //       console.log(".this.form", this.form);
    //       this.btn_loading = true;
    //       questionBankAdd(this.form).then((response) => {
    //         console.log(".addClick", response);
    //         this.$message({
    //           message: "添加成功",
    //           type: "success",
    //         });
    //         this.dialogVisible = false;
    //         this.$refs["form"].resetFields();
    //         this.answer = undefined;
    //         this.getList();
    //         this.btn_loading = false;
    //       });
    //     }
    //   });
    // },
    // 新增编辑弹窗关闭
    handleClose() {
      this.dialogVisible = false;
      this.$refs["form"].resetFields();
      this.answer = undefined;
      this.btn_loading = false;
    },
    // 导入弹窗关闭
    handleClose1() {
      this.dialogVisible1 = false;
    },
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      console.log(file);
    },
    // UploadFile(param) {
    //   let fd = new FormData();
    //   console.log("param.file", param.file);
    //   fd.append("file", param.file); // 要提交给后台的文件
    //   UploadFiles(fd)
    //     .then((response) => {
    //       console.log("提交文件", response);
    //       this.$message({
    //         message: "导入成功",
    //         type: "success",
    //       });
    //       this.dialogVisible1 = false;
    //     })
    //     .catch((err) => {
    //       console.log(err);
    //     });
    // },
    // 点击新增和编辑按钮判断
    addClick(data, id) {
      if (data === "1") {
        this.dialogVisible = true;
        this.isOption = true;
        this.diaTitle = "题目信息";
        this.form = {
          type: 1,
          name: "",
          optionA: "",
          optionB: "",
          optionC: "",
          optionD: "",
          answer: null,
        };
        this.showBtn = true;
      } else if (data === "2") {
        this.dialogVisible = true;
        this.diaTitle = "题目信息";
        this.showBtn = false;
        this.handleDetails(id);
      }
    },
    //导入按钮
    importClick() {
      this.dialogVisible1 = true;
    },
    // 编辑获取数据
    handleDetails(id) {
      questionBankDetails(id)
        .then((response) => {
          console.log(response.data.answer.toUpperCase());
          if (response.data.type == 2) {
            response.data.answer = response.data.answer.toUpperCase();
          }
          this.answer = response.data.answer.toUpperCase().split(",");
          this.form = response.data;
          // debugger;
          switch (this.form.type) {
            case 1:
            case 2: //多选
              this.isOption = true;
              break;

            default:
              this.isOption = false;
              if (this.form.answer == "A") {
                this.form.answer = "对";
              } else if (this.form.answer == "B") {
                this.form.answer = "错";
              }
              break;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 新增保存
    handleSaveAdd() {
      this.$refs["form"].validate((valid) => {
        console.log(this.answer);
        console.log(".this.form", this.form);
        if (valid) {
          let arr = [];
          if (this.answer && this.answer.length > 0) {
            for (const item of this.answer) {
              arr.push(item[0]);
            }
            this.form.answer = arr.join();
          }
          this.btn_loading = true;
          setTimeout(() => {
            this.btn_loading = false;
          }, 1000);
          if (this.form.answer == "" || !this.form.answer) {
            this.$message({
              message: "请选择答案",
              type: "warning",
            });
            return;
          }

          questionBankAdd(this.form)
            .then((response) => {
              console.log(".addClick", response);
              this.$message({
                message: "添加成功",
                type: "success",
              });
              this.dialogVisible = false;
              this.$refs["form"].resetFields();
              this.getList();
              this.btn_loading = false;
            })
            .catch((err) => {
              this.listLoading = false;
              console.log(err);
              this.btn_loading = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 编辑保存
    handleSaveEdit() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          let arr = [];
          if (this.form.type == 2 && this.answer && this.answer.length > 0) {
            for (const item of this.answer) {
              arr.push(item[0]);
            }
            this.form.answer = arr.join();
          }
          this.btn_loading = true;
          setTimeout(() => {
            this.btn_loading = false;
          }, 1000);
          if (this.form.answer == "" || !this.form.answer) {
            this.$message({
              message: "请选择答案",
              type: "warning",
            });
            return;
          }

          questionBankEdit(this.form)
            .then((response) => {
              console.log(".response", response);
              this.$message({
                message: "编辑成功",
                type: "success",
              });
              this.dialogVisible = false;
              this.$refs["form"].resetFields();
              this.getList();
              this.btn_loading = false;
            })
            .catch((err) => {
              this.listLoading = false;
              console.log(err);
              this.btn_loading = false;
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 删除
    deleteClick(id) {
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          questionBankDelete(id)
            .then((response) => {
              console.log(response);
              that.getList();
              that.$message({
                message: "删除成功",
                type: "success",
              });
            })
            .catch((err) => {
              that.listLoading = false;
              console.log(err);
            });
        })
        .catch(function () {});
    },
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
  width: 15%;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  float: left;
}
.line {
  width: 85%;
  border-bottom: 3px solid #333399;
  position: absolute;
  left: 15%;
  top: 50%;
  z-index: 1;
  float: left;
}
</style>