<template>
  <div class="app-container">
    <div style="text-align: center; height: 36px; line-height: 36px">
      <span v-if="month">
        {{ month[0] ? month[0] : null }}年{{ month[1] ? month[1] : null }}
      </span>
      月供应商评价表
      <!-- <buttonGroup
        oneButton="返回"
        twoButton="保存"
        threeButton="导出"
        @handleFilter="backClick"
        @handleAdd="editClick()"
        style="float: right"
      /> -->
      <div  style="float: right">
         <el-button
        type="primary"
        @click="backClick()"
        
        >返回</el-button
      >
      <!--size="small"  -->
        <el-button type="primary"
        @click="editClick()"
        
          >保存</el-button
        >
      </div>
    </div>

    <div style="margin: 20px 0">
      供应商名称：<span v-if="name"> {{ name }}</span>
    </div>
    <el-table
      :data="tableData"
      :span-method="arraySpanMethod"
      border
      style="width: 100%"
    >
      <!-- <el-table-column prop="id" label="ID" width="180"> </el-table-column> -->
      <el-table-column prop="type" label="评价部分">
        <template slot-scope="scope">
          <span>{{scope.row.type==1?'技术部分':'商业部分'}}</span>
        </template>
         </el-table-column>
      <el-table-column prop="dimension" label="维度"> </el-table-column>
      <el-table-column prop="content" label="评价内容"> </el-table-column>
      <el-table-column prop="method" label="评价方法"> </el-table-column>
      <el-table-column prop="score" label="得分（满分100分）">
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
          v-if="scope.row.type==1"
            @click="handleUpdate(scope.row)"
            type="primary"  plain
            >编辑</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <div style="display: flex; margin-top: 30px">
      <div>上传附件</div>
      <el-upload
        style="float: left; margin-left: 10px"
        drag
        ref="upload"
        class="upload-btn"
        action="string"
        :file-list="fileList"
        :before-upload="onBeforeUploadImage"
        :http-request="UploadFile"
        :before-remove="handleRemove"
        multiple
      >
        <!-- accept=".xls, .xlsx" -->
        <i class="el-icon-upload"></i>
        <div class="el-upload__text">
          将文件拖到此处，或
          <em>点击上传</em>
        </div>
        <!-- <div class="el-upload__tip" slot="tip">
              <a href="./template.xlsx" download="下载模板">下载模板</a>
            </div> -->
      </el-upload>
      <el-dialog :title="title" :visible.sync="open">
        <el-form
          ref="elForm"
          :model="formData"
          size="medium"
          label-width="100px"
        >
          <el-form-item label="得分" prop="score">
            <el-input
              v-model="formData.score"
              placeholder="请输入得分"
              clearable
              :style="{ width: '100%' }"
            >
            </el-input>
          </el-form-item>
        </el-form>
        <div slot="footer">
          <el-button type="primary" @click="handelConfirm">确定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import {
  F13ContractEvaluateInfo,
  F14ContractEvaluateMetricsInfo,
  F13ContractEvaluateMetricsEditSave,
  F12ContractEvaluateEditSave
} from "@/api/partner";
import buttonGroup from "@/views/components/com/buttonGroup";
import { UploadFiles } from "@/api/upLoad/persomUpload";
export default {
  data() {
    return {
      fileList: [],
      month: null,
      name: null,
      tableData: [],
      title: "编辑",
      open: false,
      formData: {
        id: undefined,
        score: undefined,
      },
    };
  },
  components: {
    buttonGroup,
  },
  methods: {
    arraySpanMethod({ row, column, rowIndex, columnIndex }) {
      console.log({ row, column, rowIndex, columnIndex });
      console.log(this.count);
      //   if (rowIndex % 2 === 0) {
      //     if (columnIndex === 0) {
      //       return [1, 2];
      //     } else if (columnIndex === 1) {
      //       return [0, 0];
      //     }
      //   }
      //   if (rowIndex == this.count) {
      //     return [1, 2, 3, 4];
      //   }
    },
    handelConfirm() {
      F13ContractEvaluateMetricsEditSave(this.formData).then((res) => {
        this.getList();
        this.open = false;
      });
    },
    onBeforeUploadImage(val) {
      console.log(val);
    },
    UploadFile(param) {
      let fd = new FormData();
      console.log("param.file", param);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then((response) => {
          console.log("提交文件", response.msg);
          // param.file.src = response.msg;
          // this.formData.fileUrls.push(param.file);
          this.fileList.push(...this.dataHandle([response.msg]));
          this.$message({
            message: "导入成功",
            type: "success",
          });
          this.dialogVisible1 = false;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleRemove(file, fileList) {
      //TODO:
      // console.log(this.formData.fileUrls);
      for (const [index, item] of this.fileList.entries()) {
        if (item.name == file.name) {
          console.log(index);
          this.fileList.splice(index, 1);
        }
      }

      console.log(file, fileList);
    },
    // 数据处理
    dataHandle(val) {
         if (!val) {
        return []
      }
      let arr = [];
      for (const item of val) {
        let obj = {};
        obj.name = item.split("pfserverperson/")[1];
        obj.src = item;
        arr.push(obj);
      }
      return arr;
    },

    backClick() {
      console.log("返回");
      this.$router.go(-1);
    },
    editClick() {
      console.log("修改");
      let fileUrls = []
      for (const item of this.fileList) {
        // fileUrls+=item.src+','
        fileUrls.push(item.src)
      }
      let id = this.$route.query.id
      let obj = {
        id,
        fileUrls
      }
      console.log(obj);
      F12ContractEvaluateEditSave(obj).then().then(this.backClick())
    },
    handleUpdate(row) {
      console.log();
      F14ContractEvaluateMetricsInfo(row.id).then((res) => {
        this.formData = res.data;
        this.open = true;
      });
      console.log("编辑");
    },
    handleImportant() {
      console.log("导出");
    },
    getList() {
      let { id, typeId, month } = this.$route.query;

      F13ContractEvaluateInfo({ id, typeId }).then((res) => {
        this.name = res.data.carrierName;
        console.log(res);
        this.count = 0;
        this.totalScore = res.data.totalScore;
      this.fileList.push(...this.dataHandle(res.data.fileList));

        for (const item of res.data.pContractEvaluateMetricsList) {
          if (item.type == 1) {
            this.count++;
          }
        }
        this.tableData = res.data.pContractEvaluateMetricsList;
      });
    },
  },
  mounted() {
    console.log(this.$route.query);
    let { id, typeId, month } = this.$route.query;
    this.month = month ? month.split("-") : ["", ""];
    console.log(this.month);
    this.getList();
  },
};
</script>

<style lang="scss" scoped>
</style>