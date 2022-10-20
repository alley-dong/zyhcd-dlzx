<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
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
      <el-form-item style="float:right">
        <el-row :gutter="20">
          <el-col :span="8">
            <el-button
              type="primary"
              icon="el-icon-search"
              size="small "
              @click="handleQuery"
              >查询</el-button
            >
          </el-col>
          <el-col :span="8">
            <router-link :to="{ path: '/addCard' }">
              <el-button
                type="primary"
                icon="el-icon-plus"
                size="small "
                v-hasPermi="['car:etcCard:add']"
                >新增</el-button
              >
            </router-link>
          </el-col>
          <el-col :span="8">
            <el-button
              type="warning"
              icon="el-icon-download"
              size="small "
              @click="importClick()"
              v-hasPermi="['car:etcCard:import']"
              >导入</el-button
            >
          </el-col>
        </el-row>
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
        >导出</el-button>
      </el-col>
	  <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table
      v-loading="loading"
      border
      :data="cardList"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column
        type="index"
        :index="typeIndex"
        label="序号"
        align="center"
      ></el-table-column>
      <!-- <el-table-column label="主键" align="center" prop="id" /> -->
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="ETC卡号" align="center" prop="etcCardNumber" />
      <el-table-column label="订单号" align="center" prop="orderNumber" />
      <el-table-column
        label="订单时间"
        align="center"
        prop="orderTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单状态" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status | state }}
        </template>
      </el-table-column>
      <el-table-column label="领取方式" align="center" prop="method" />
      <el-table-column label="OBU号" align="center" prop="obu" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['car:etcCard:edit']"
            >编辑</el-button>

          <el-button
            type="danger"
            @click="handleDelete(scope.row.id)"
             v-hasPermi="['car:etcCard:delete']"
            >删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改ETC管理-ETC卡对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车辆ID" prop="vehicleId">
          <el-input v-model="form.vehicleId" placeholder="请输入车辆ID" />
        </el-form-item>
        <el-form-item label="车牌号" prop="licensePlate">
          <el-input v-model="form.licensePlate" placeholder="请输入车牌号" />
        </el-form-item>
        <el-form-item label="ETC卡号" prop="etcCardNumber">
          <el-input v-model="form.etcCardNumber" placeholder="请输入ETC卡号" />
        </el-form-item>
        <el-form-item label="订单号" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="订单时间" prop="orderTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 200px"
            v-model="form.orderTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择订单时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单状态 1已发货 2未发货">
          <el-radio-group v-model="form.status">
            <el-radio label="1">请选择字典生成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="领取方式" prop="method">
          <el-input v-model="form.method" placeholder="请输入领取方式" />
        </el-form-item>
        <el-form-item label="OBU号" prop="obu">
          <el-input v-model="form.obu" placeholder="请输入OBU号" />
        </el-form-item>
        <el-form-item label="删除状态 1正常 2删除" prop="deleteFlag">
          <el-input
            v-model="form.deleteFlag"
            placeholder="请输入删除状态 1正常 2删除"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 导入弹窗-->
    <el-dialog
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
            <a href="/etcManagement.xlsx" download="ETC管理模板.xlsx">下载模板</a>
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
  listCard,
  addCard,
  cardSave,
  cardEdit,
  etcCardBox,
  etcCardImprot,
  delCard
} from "@/api/etc/card";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "Card",
  components: {
    buttonGroup
  },
  data() {
    return {
      saveLoading: false,
      total: 10,
      listLoading: false,
      tableData: [],
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
      // ETC管理-ETC卡表格数据
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        licensePlate: "",
        etcCardNumber: ""
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      //导入弹窗
      dialogVisible1: false
    };
  },
  filters: {
    state: function(value) {
      switch (value) {
        case 1:
          return "已发货";
          break;
        case 2:
          return "未发货";
          break;
        default:
          break;
      }
    }
  },
  created() {
    this.getList();
  },
  methods: {
    //导入按钮
    importClick() {
      this.dialogVisible1 = true;
    },
    // 导入弹窗关闭
    handleClose1() {
      this.dialogVisible1 = false;
    },
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      console.log(file);
    },
    uploadClick() {
      if (this.upfile) {
        console.log(this.upfile);
        let fd = new FormData();
        fd.append("file", this.upfile); // 要提交给后台的文件
        etcCardImprot(fd)
          .then(response => {
            console.log("提交文件", response);
            this.$message({
              message: "导入成功",
              type: "success"
            });
            this.dialogVisible1 = false;
            this.getList();
          })
          .catch(err => {
            console.log(err);
          });
      } else {
        this.$message({
          message: "请导入文件"
        });
      }
    },
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
    },

    //显示序号
    typeIndex(index) {
      return (
        (this.queryParams.pageNum - 1) * this.queryParams.pageSize + index + 1
      );
    },
    /** 查询ETC管理-ETC卡列表 */
    getList() {
      this.loading = true;
      listCard(this.queryParams)
        .then(response => {
          this.cardList = response.rows;
          this.total = response.total;
          this.loading = false;
        })
        .catch(err => {
          this.loading = false;
          console.log(err);
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
        etcCardNumber: null,
        orderNumber: null,
        orderTime: null,
        status: 0,
        method: null,
        obu: null,
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
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    // handleSelectionChange(selection) {
    //   this.ids = selection.map(item => item.id)
    //   this.single = selection.length!==1
    //   this.multiple = !selection.length
    // },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加ETC管理-ETC卡";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // this.reset();
      // const id = row.id || this.ids;
      // getCard(id).then((response) => {
      //   this.form = response.data;
      //   this.open = true;
      //   this.title = "修改ETC管理-ETC卡";
      // });
      this.$router.push({ path: "/editEtcCard", query: { id: row.id } });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCard(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCard(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(id) {
      this.$confirm('是否确认删除', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return delCard(id);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
      // delCard(id).then(res => this.getList());
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/card/export",
        {
          ...this.queryParams
        },
        `system_card.xlsx`
      );
    }
  }
};
</script>
