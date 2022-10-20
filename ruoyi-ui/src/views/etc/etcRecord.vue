<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="开始时间" prop="firstTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.firstTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="finishTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.finishTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          placeholder="请输入车牌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="ETC卡号" prop="etcCardNumber">
        <el-input
          v-model="queryParams.etcCardNumber"
          placeholder="请输入ETC卡号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-button
        type="primary"
        size="small "
        style="float: right; margin-right:10px"
        @click="datacheck()"
        >数据校验</el-button
      >
      <el-button
        type="warning"
        icon="el-icon-download"
        size="small "
        style="float: right;margin-right:13px"
        @click="importClick()"
        v-hasPermi="['car:etcCard:import']"
        >导入</el-button
      >
      <el-button
        type="cyan"
        icon="el-icon-search"
        size="small "
        style="float: right; margin-right:5px"
        @click="handleQuery"
        >查询</el-button
      >
    </el-form>
    <el-table
      v-loading="loading"
      border
      :data="recordList"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="ETC卡号" align="center" prop="etcCardNumber" />
      <el-table-column label="消费金额" align="center" prop="amount" />
      <el-table-column
        label="入站时间"
        align="center"
        prop="inboundTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.inboundTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="出站时间"
        align="center"
        prop="outboundTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.outboundTime }}</span>
        </template>
      </el-table-column>
      <el-table-column label="路段地点" align="center" prop="roadSection" />
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 导入弹窗-->
    <el-dialog
      :title="diaTitle1"
      :visible.sync="dialogVisible1"
      width="600px"
      :before-close="handleClose1"
    >
      <div style="width:640px; height:200px;">
        <el-form
          ref="form"
          :model="form"
          label-width="100px"
          style="float:left;"
        >
          <el-form-item label="导入文件："></el-form-item>
        </el-form>
        <el-upload
          style="float:left; margin-left:10px;"
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
            <a href="/etcRecords.xls" download="ETC记录导入模板.xls">下载模板</a>
          </div>
        </el-upload>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="uploadClick">导入</el-button>
      </span>
    </el-dialog>

    <!-- 数据校验导入 -->
    <el-dialog
      :title="diaTitle2"
      :visible.sync="dialogVisible2"
      width="600px"
      :before-close="handleClose2"
    >
      <el-row>
        <el-form
          ref="form"
          :model="form"
          label-width="100px"
          style="float:left;"
        >
          <el-col :span="24">
            <el-form-item label="开始时间" prop="firstTime">
              <el-date-picker
                clearable
                size="small"
                style="width: 200px"
                v-model="queryParams.firstTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择开始时间"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>

          <el-col :span="24">
            <el-form-item label="结束时间" prop="finishTime">
              <el-date-picker
                clearable
                size="small"
                style="width: 200px"
                v-model="queryParams.finishTime"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="选择结束时间"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span="4">
            <el-form-item label="导入文件："></el-form-item>
          </el-col>
          <el-col :span="20">
            <el-upload
              style="float:left; margin-left:10px;"
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
                <a href="/etcRecords.xls" download="ETC记录导入模板.xls"
                  >下载模板</a
                >
              </div>
            </el-upload>
          </el-col>
        </el-form>
      </el-row>
      <span slot="footer" class="dialog-footer">
        <el-button @click="uploadClick1">导入</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import { listRecord, J05Check, J05ImportData } from "@/api/etc/record";

export default {
  name: "Record",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // ETC管理-ETC卡使用记录表格数据
      recordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        licensePlate: null,
        etcCardNumber: null,
        firstTime: this.parseTime(new Date(),'{y}-{m}')+'-01',
        finishTime: this.parseTime(new Date(),'{y}-{m}-{d}')
      },
      //导入弹窗
      dialogVisible1: false,
      dialogVisible2: false,
      centerDialogVisible: false,
      diaTitle2: "数据校验",
      diaTitle1: "导入",
      // 表单参数
      form: {},
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询ETC管理-ETC卡使用记录列表 */
    getList() {
      this.loading = true;
      listRecord(this.queryParams).then(response => {
        this.recordList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        vehicleId: null,
        licensePlate: null,
        etcCardId: null,
        etcCardNumber: null,
        amount: null,
        inboundTime: null,
        outboundTime: null,
        roadSection: null,
        deleteFlag: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      var beginTime = this.queryParams.firstTime;
      var endTime = this.queryParams.finishTime;
      if (beginTime && endTime && beginTime > endTime) {
        this.$message({
          showClose: true,
          message: "开始时间不能大于结束时间",
          type: "warning"
        });
        return;
      }
      this.getList();
    },
    //导入按钮
    importClick() {
      this.dialogVisible1 = true;
    },
    //数据校验
    datacheck() {
      this.dialogVisible2 = true;
    },
    //搜索时间比较
    timeCompare(val) {
      return parseInt(new Date(val).getTime() / 1000);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 导入弹窗关闭
    handleClose1() {
      this.dialogVisible1 = false;
    },
    // 数据校验关闭
    handleClose2() {
      this.dialogVisible2 = false;
    },
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      console.log(file);
    },
    UploadFile(param) {
      let fd = new FormData();
      console.log("param.file", param.file);
      fd.append("file", param.file); // 要提交给后台的文件
      J05ImportDatas(fd)
        .then(response => {
          console.log("提交文件", response);
          this.$message({
            message: "导入成功",
            type: "success"
          });
          this.dialogVisible1 = false;
        })
        .catch(err => {
          console.log(err);
        });
    },
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
    },

    uploadClick() {
      if (this.upfile) {
        console.log(this.upfile);
        let fd = new FormData();
        fd.append("file", this.upfile); // 要提交给后台的文件
        J05ImportData(fd)
          .then(response => {
            console.log("提交文件", response);
            this.$message({
              message: response.msg,
              type: "success"
            });
            this.dialogVisible1 = false;
            this.getList();
          })
          .catch(err => {
            console.log(err);
          });
      } else {
      }
    },
    // 数据校验
    uploadClick1() {
      if (this.upfile) {
        console.log(this.upfile);
        let fd = new FormData();
        fd.append("file", this.upfile); // 要提交给后台的文件
        fd.append("firstTime", this.queryParams.firstTime);
        fd.append("finishTime", this.queryParams.finishTime);
  this.download1(
          "/pfserver-car/car/etcCardUsageRecord/J05Check",
            fd,
          `异常记录.xls`
        );
      } else {
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/record/export",
        {
          ...this.queryParams
        },
        `system_record.xlsx`
      );
    }
  }
};
</script>
