<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="90px"
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
      <el-form-item label="车辆VIN" prop="frameNumber">
        <el-input
          v-model="queryParams.frameNumber"
          placeholder="请输入车辆VIN"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="卡号/流水号" prop="cardNumber">
        <el-input
          v-model="queryParams.cardNumber"
          placeholder="请输入卡号/流水号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item style="float: right">
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleQuery"
          >查询</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="small "
          @click="handleAdd"
          v-hasPermi="['pfservercar:fuelRechargeCard:add']"
          >新增</el-button
        >
        <el-button
          type="warning"
          icon="el-icon-download"
          size="small "
          @click="importClick()"
          v-hasPermi="['pfservercar:fuelRechargeCard:import']"
          >导入</el-button
        >
      </el-form-item>
    </el-form>

    <el-table v-loading="loading" :data="cardList" border>
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="车辆VIN" align="center" prop="frameNumber" />
      <el-table-column label="卡号/流水号" align="center" prop="cardNumber" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small "
            type="primary"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfservercar:fuelRechargeCard:info']"
            style="color: #fff"
            >编辑</el-button
          >
          <el-button
            size="small "
            type="danger"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfservercar:fuelRechargeCard:delete']"
            style="color: #fff"
            >删除</el-button
          >
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

    <!-- 添加话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form
        ref="addform"
        :model="addform"
        :rules="rules"
        label-width="90px"
        :hide-required-asterisk="true"
      >
        <el-form-item label="车牌号" prop="licensePlate">
          <!-- <el-select
            v-model="addform.licensePlate"
            placeholder="请选车牌号："
            @change="addquotaRouterBoxData"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in routerOptions"
              :key="item.id"
              :label="item.licensePlate"
              :value="item.id"
            ></el-option>
          </el-select> -->
          <el-autocomplete
            class="inline-input"
            v-model="addform.licensePlate"
            :fetch-suggestions="querySearch1"
            placeholder="请输入车牌号"
            @select="handleSelect1"
            :style="{ width: '100%' }"
          >
            <template slot-scope="{ item }" :style="{ width: '100%' }">
              <!-- <div class="name">{{ item.id }}</div> -->
              <span class="addr">{{ item.licensePlate }}</span>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="车辆VIN" prop="frameNumber">
          <el-input
            v-model="addform.frameNumber"
            placeholder="请输入车辆VIN"
            :disabled="true"
          />
        </el-form-item>
        <el-form-item label="卡号/流水号" prop="cardNumber">
          <el-input
            v-model="addform.cardNumber"
            placeholder="请输入卡号/流水号"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="btn_loading" @click="addclick"
          >确 定</el-button
        >
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改话框 -->
    <el-dialog
      :title="title"
      :visible.sync="updateopen"
      width="500px"
      append-to-body
    >
      <el-form ref="addform" :model="addform" :rules="rules" label-width="90px">
        <el-form-item label="车牌号" prop="licensePlate">
          <!-- <el-select
            v-model="updateform.licensePlate"
            placeholder="请选择车牌号"
            @change="updatequotaRouterBoxData"
            clearable
            :style="{ width: '100%' }"
            :disabled="true"
          >
            <el-option
              v-for="item in routerOptions"
              :key="item.id"
              :label="item.licensePlate"
              :value="item.id"
            ></el-option>
          </el-select> -->
          <el-autocomplete
            class="inline-input"
            v-model="updateform.licensePlate"
            :fetch-suggestions="querySearch"
            placeholder="请输入车牌号"
            @select="handleSelect"
            :style="{ width: '100%' }"
          >
            <template slot-scope="{ item }" :style="{ width: '100%' }">
              <!-- <div class="name">{{ item.id }}</div> -->
              <span class="addr">{{ item.licensePlate }}</span>
            </template>
          </el-autocomplete>
        </el-form-item>
        <el-form-item label="车辆VIN" prop="frameNumber">
          <el-input
            v-model="updateform.frameNumber"
            placeholder="请输入车辆VIN"
            :disabled="true"
          />
        </el-form-item>
        <el-form-item label="卡号/流水号" prop="cardNumber">
          <el-input
            v-model="updateform.cardNumber"
            placeholder="请输入卡号/流水号"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateclick">确 定</el-button>
        <el-button @click="cancell">取 消</el-button>
      </div>
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
            <a href="/chargeCardManagement.xlsx" download="充电卡管理">下载模板</a>
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
  RechargeCardAdd,
  getRechargeCardList,
  FuelRechargeCardInfo,
  FuelRechargeCardDelete,
  I10FuelRechargeCardImport,
  J02CarNumList,
} from "@/api/card/chargeableBattery";
export default {
  name: "Card",
  data() {
    return {
      btn_loading: false,
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
      // 油卡定额管理表格数据
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      updateopen: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        licensePlate: "",
        frameNumber: "",
        cardNumber: "",
      },
      form: {},
      // 新增参数
      addform: {
        id: "",
        licensePlate: "",
        cardNumber: "",
      },

      //更新参数
      updateform: {
        id: undefined,
        licensePlate: "",
        frameNumber: "",
        cardNumber: "",
      },
      vehicleOptions: [],
      // 线路下拉
      routerOptions: [],

      // 表单校验
      rules: {},
      //导入弹窗
      dialogVisible1: false,
      diaTitle1: "导入",
      vehicleModelNamelist: [],
      oilslist: [],
    };
  },
  created() {
    this.routerselect();
    this.getList();
  },

  methods: {
    handleSelect1(item) {
      console.log(item);
      // this.addform.licensePlate = item.licensePlate;
      this.addform.frameNumber = item.frameNumber;
      this.addform.id = item.id;
      this.addform.licensePlate = item.licensePlate;
    },
    querySearch1(queryString, cb) {
      var restaurants = this.routerOptions;
      var results = queryString
        ? restaurants.filter(this.createFilter1(queryString))
        : restaurants;
      cb(results);
    },
        handleSelect(item) {
      console.log(item);
      // this.addform.licensePlate = item.licensePlate;
      this.updateform.frameNumber = item.frameNumber;
      // this.updateform.id = item.id;
      this.updateform.licensePlate = item.licensePlate;
    },
    querySearch(queryString, cb) {
      var restaurants = this.routerOptions;
      var results = queryString
        ? restaurants.filter(this.createFilter1(queryString))
        : restaurants;
      cb(results);
    },
    createFilter1(queryString) {
      return (restaurant) => {
        return (
          restaurant.licensePlate
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    // 路线下拉
    routerselect() {
      J02CarNumList({}).then((res) => {
        this.routerOptions = res.data;
      });
    },
    addquotaRouterBoxData(val) {
      for (const item of this.routerOptions) {
        if (val == item.id) {
          this.addform.frameNumber = item.frameNumber;
          this.addform.id = item.id;
          this.addform.licensePlate = item.licensePlate;
        }
      }
    },
    // 修改
    updatequotaRouterBoxData(val) {
      for (const item of this.routerOptions) {
        if (val == item.id) {
          this.updateform.frameNumber = item.frameNumber;
          // this.addform.id = item.id;
          this.updateform.licensePlate = item.licensePlate;
        }
      }
    },
    //  燃料管理-充电卡管理列表
    getList() {
      this.loading = true;
      getRechargeCardList(this.queryParams).then((response) => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    cancell() {
      this.updateopen = false;
      this.reset();
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    reset() {
      this.addform = {
        id: "",
        licensePlate: "",
        frameNumber: "",
        cardNumber: "",
      };
    },

    /** 新增按钮操作 */
    handleAdd(row) {
      this.open = true;
      this.title = "添加充电卡管理";
      this.reset();
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.updateopen = true;
      this.title = "修改充电卡管理";
      FuelRechargeCardInfo(
        JSON.stringify({ licensePlate: row.licensePlate })
      ).then((res) => {
        this.updateform = res.data;
      });
    },
    // 新增保存按钮
    addclick() {
      // this.$refs["form"].validate(valid => {
      // if (valid) {
      this.btn_loading = true;
      setTimeout(() => {
        this.btn_loading = false;
      }, 1000);
      RechargeCardAdd(this.addform).then((response) => {
        this.msgSuccess("新增成功");
        this.open = false;
        this.getList();
      });
      //   }
      // });
    },
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
        I10FuelRechargeCardImport(fd)
          .then((response) => {
            console.log("提交文件", response);
            this.$message({
              message: "导入成功",
              type: "success",
            });
            this.dialogVisible1 = false;
            this.getList();
          })
          .catch((err) => {
            console.log(err);
            this.getList();
          });
      } else {
        this.getList();
      }
    },
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
    },

    // 修改保存按钮
    updateclick() {
      // this.$refs["form"].validate(valid => {
      // if (valid) {
      RechargeCardAdd(this.updateform).then((response) => {
        this.msgSuccess("修改成功");
        this.updateopen = false;
        this.getList();
      });
      //   }
      // });
    },

    handleClick(row) {
      console.log(row);
      this.$router.push({ path: "/see/" + row.id });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      var list = {
        licensePlate: row.licensePlate,
        id: row.id,
      };
      this.$confirm(
        '是否确认删除油卡管理编号为"' + row.licensePlate + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return FuelRechargeCardDelete(list);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
  },
};
</script>
