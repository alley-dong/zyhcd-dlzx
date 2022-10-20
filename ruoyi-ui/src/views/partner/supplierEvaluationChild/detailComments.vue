<template>
  <div class="app-container">
    <div style="text-align: center; height: 36px; line-height: 36px">
      <span v-if="month">
        {{ month[0] ? month[0] : null }}年{{ month[1] ? month[1] : null }}
      </span>
      月供应商评价表
      <!-- <buttonGroup
        oneButton="返回"
        twoButton="编辑"
        threeButton="导出"
        @handleFilter="backClick"
        @handleAdd="editClick()"
        @handleImportant="handleImportant"
        leadingIn
        style="float: right"
      /> -->
      <div style="float: right">
        <el-button type="primary" @click="backClick()">返回</el-button>
        <!--size="small"  -->
        <el-button type="primary" @click="editClick()">编辑</el-button>
        <!--size="small"  -->
        <el-button type="primary" @click="handleOpen()">导出</el-button>
        <!--size="small"  -->
      </div>
    </div>

    <div style="margin: 20px 0">
      供应商名称：<span v-if="name"> {{ name }}</span>
    </div>
    <el-table
      :data="tableData"
      :span-method="arraySpanMethod"
      sum-text="供应商"
      border
      style="width: 100%"
    >
      <!-- <el-table-column prop="id" label="ID" width="180"> </el-table-column> -->
      <el-table-column align="left" prop="typeEnum" label="评价部分">
        <!-- <template slot-scope="scope">
              <span>{{scope.row.type==1?'商业部分':'技术部分'}}</span>
            </template> -->
      </el-table-column>
      <el-table-column align="left" prop="dimension" label="维度">
      </el-table-column>
      <el-table-column align="left" prop="content" label="评价内容">
      </el-table-column>
      <el-table-column align="left" prop="method" label="评价方法">
      </el-table-column>
      <el-table-column align="left" prop="score" label="得分（满分100分）">
      </el-table-column>
      <!-- <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['car:item:edit']"
            >编辑</el-button
          >
         
        </template>
      </el-table-column> -->
    </el-table>
    <div style="display: flex; margin-top: 30px">
      <div>附件</div>
      <div class="my_upload">
        <el-upload
          disabled
          style="float: left; margin-left: 10px"
          drag
          ref="upload"
          class="upload-btn"
          action="string"
          accept=".xls, .xlsx"
          :file-list="fileList"
          :before-upload="onBeforeUploadImage"
          :http-request="UploadFile"
          :before-remove="handleRemove"
          :on-preview="uploadPreview"
          multiple
        >

        </el-upload>
      </div>
    </div>
    <div style="margin-top: 30px">
      <span style="margin-right: 30px">评价时间：{{ createTime }}</span>
      <span>评价人：{{ createBy }}</span>
    </div>
    <el-dialog title="选择年度预算" :visible.sync="open">
      <el-form :model="form">
        <el-form-item label="选择年度预算">
          <el-select v-model="form.value" placeholder="请选择年度预算">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <div style="display: flex">
          <el-button
            style="margin: auto"
            @click="handleImportant"
            type="primary"
            >确认</el-button
          >
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { F13ContractEvaluateInfo, evaluateExport } from "@/api/partner";
import { minioChooseBucketUploa, minioChooseBucketDownload } from "@/api/com";
import axios from "axios";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  data() {
    return {
      month: null,
      name: null,
      tableData: [],
      fileList: [],
      createBy: null,
      createTime: null,
      open: false,
      options: [
        {
          label: "年度预算＜200万元",
          value: 1,
        },
        {
          label: "年度预算≥200万元",
          value: 2,
        },
      ],
      form: {
        value: undefined,
      },
      tableDatalength: undefined,
      tableDatalength1: undefined,
    };
  },
  components: {
    buttonGroup,
  },
  methods: {
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      // console.log(file);
    },
    handleRemove(file, fileList) {
      // console.log(this.formData.fileUrls);
      for (const [index, item] of this.formData.fileUrls.entries()) {
        if (item.name == file.name) {
          // console.log(index);
          this.formData.fileUrls.splice(index, 1);
        }
      }

      // console.log(file, fileList);
    },
    UploadFile(param) {
      return;
      // let fd = new FormData();
      // // console.log("param.file", param);
      // fd.append("file", param.file); // 要提交给后台的文件
      // minioChooseBucketUpload(fd)
      //   .then((response) => {
      //     // console.log("提交文件", response.msg);
      //     param.file.src = response.msg;
      //     this.formData.fileUrls.push(param.file);
      //     this.$message({
      //       message: "导入成功",
      //       type: "success",
      //     });
      //     this.dialogVisible1 = false;
      //   })
      //   .catch((err) => {
      //     // console.log(err);
      //   });
    },
    uploadPreview(file) {
      // console.log(file);
      if (file.name.indexOf(".") == -1) {
        return;
      }
      let obj = {
        originalName: file.name,
        filename: file.name,
      };
      window.location.href =
        process.env.VUE_APP_BASE_API +
        `/pfserver-person/personFile/minioChooseBucketDownload?originalName=${obj.originalName}&filename=${obj.originalName}`;
      // window.open(
      //   `http://localhost/dev-api/pfserver-person/personFile/minioChooseBucketDownload?originalName=${obj.originalName}&filename=${obj.originalName}`
      // );
    },
    arraySpanMethod({ row, column, rowIndex, columnIndex }) {
      const sums = [];

      if (!row.id) {
        // console.log(column);
        // row.type = 111;

        row.score = "99";
        return [1, 4];
      }
      // if (columnIndex === 0) {
      //   if (rowIndex<=this.tableDatalength&&(rowIndex % this.tableDatalength === 0)) {
      //     return {
      //       rowspan: this.tableDatalength,
      //       colspan: 1,
      //     };
      //   } else if (rowIndex>this.tableDatalength+1&&(((rowIndex-this.tableDatalength+2) % this.tableDatalength1) === 0)) {
      //      return {
      //       rowspan:this.tableDatalength1,
      //       colspan: 1,
      //     };
      //   } else {
      //     return {
      //       rowspan: 0,
      //       colspan: 0,
      //     };
      //   }
      //   /*

      //  */
      // }
    },
    backClick() {
      // console.log("返回");
      this.$router.go(-1);
    },
    editClick() {
      // console.log(this.$route.query);
      this.$router.push({
        path: "/newComments/" + this.$route.query.id,
        query: {
          id: this.$route.query.id,
          typeId: this.$route.query.typeId,
          month: this.$route.query.month,
        },
      });
    },
    handleUpdate() {},
    handleOpen() {
      this.open = true;
    },
    handleImportant() {
      if (!this.form.value) {
        this.$message({
          message: "请选择年度预算",
          type: "warning",
        });
        return;
      }
      // this.download1(
      //   "/pfserver-person/evaluate/export",
      //   {
      //     // ...this.queryParams
      //     id: this.$route.params.id,
      //     yearMoneyType:this.form.value
      //   },
      //   `供应商评价表.xlsx`
      // );
      const baseURL = process.env.VUE_APP_BASE_API;
      window.location.href =
        baseURL +
        "/pfserver-person/evaluate/export?id=" +
        this.$route.params.id +
        "&yearMoneyType=" +
        this.form.value;
      // /evaluate/export?id=8d0cb9b1-0763-467f-b6e7-72e97ac2b757&yearMoneyType=1
    },
    // 数据处理
    dataHandle(val) {
      console.log(val);
      if (!val) {
        return [];
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
    exportData() {
      //   const form = this.getSearchForm(); // 要发送到后台的数据
      const form = { id: this.$route.params.id };
      axios({
        // 用axios发送post请求
        method: "post",
        url: "/pfserver-person/evaluate/export", // 请求地址
        data: form, // 参数
        responseType: "blob", // 表明返回服务器返回的数据类型
      }).then((res) => {
        // 处理返回的文件流
        const content = res;
        const blob = new Blob([content]);
        const fileName = "测试表格123.xls";
        if ("download" in document.createElement("a")) {
          // 非IE下载
          const elink = document.createElement("a");
          elink.download = fileName;
          elink.style.display = "none";
          elink.href = URL.createObjectURL(blob);
          document.body.appendChild(elink);
          elink.click();
          URL.revokeObjectURL(elink.href); // 释放URL 对象
          document.body.removeChild(elink);
        } else {
          // IE10+下载
          navigator.msSaveBlob(blob, fileName);
        }
      });
    },
  },

  mounted() {
    // console.log(this.$route.query);
    let { id, typeId, month } = this.$route.query;
    month = JSON.parse(JSON.stringify(month));
    this.month = month ? month.split("-") : ["", ""];
    // console.log(this.month);
    F13ContractEvaluateInfo({ id, typeId }).then((res) => {
      this.name = res.data.carrierName;
      // console.log(res);
      this.count = 0;
      this.totalScore = res.data.totalScore;
      if (res.data.fileList) {
        this.fileList.push(...this.dataHandle(res.data.fileList));
      }
      this.createBy = res.data.createBy;
      this.createTime = res.data.createTime;
      for (const item of res.data.pContractEvaluateMetricsList) {
        if (item.type == 1) {
          this.count++;
        }
      }
      let list = res.data.pContractEvaluateMetricsList.filter(
        (item) => item.type == 1
      );
      let list1 = res.data.pContractEvaluateMetricsList.filter(
        (item) => item.type == 2
      );
      // for (const item of list) {
      //   item.type = "商业部分";
      // }
      // for (const item of list1) {
      //   item.type = "技术部分";
      // }
      console.log(list, list1);
      this.tableDatalength = list.length;
      this.tableDatalength1 = list1.length;
      //   处理数据
      this.tableData = [
        ...list,
        {
          typeEnum: "总计",
          dimension: res.data.totalScore,
        },
        {
          typeEnum: "乘以权重70%得分",
          dimension: res.data.weightTotalScore,
        },
        ...list1,
        {
          typeEnum: "总计",
        },
        {
          typeEnum: "乘以权重30%得分",
        },
      ];
      //   this.tableData = res.data.pContractEvaluateMetricsList;
    });
  },
};
</script>

<style lang="scss" scoped>
</style>
<style lang="scss">
.my_upload {
  .el-upload-dragger {
    display: none;
  }
}
</style>