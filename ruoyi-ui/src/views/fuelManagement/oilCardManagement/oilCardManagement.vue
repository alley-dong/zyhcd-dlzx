<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="form"
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
      <el-form-item label="油卡卡号" prop="oilCardNumber">
        <el-input
          v-model="queryParams.oilCardNumber"
          placeholder="请输入油卡卡号"
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
          v-hasPermi="['pfservercar:card:add']"
          >新增</el-button
        >
        <el-button
          type="warning"
          icon="el-icon-download"
          size="small "
          @click="importClick()"
          v-hasPermi="['pfservercar:card:import']"
          >导入</el-button
        >
      </el-form-item>
    </el-form>

    <el-table
      v-loading="loading"
      :data="cardList"
      border
      :row-class-name="tableRowClassName"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="油卡卡号" align="center" prop="oilCardNumber" />
      <el-table-column label="油品" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.oils === 1">柴油</span>
          <span v-if="scope.row.oils === 2">汽油</span>
        </template>
      </el-table-column>
      <el-table-column
        label="办卡时间"
        align="center"
        prop="cardTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.cardTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="到期时间"
        align="center"
        prop="dueTime"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dueTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
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
            v-hasPermi="['pfservercar:card:edit']"
            style="color: #fff"
            >修改</el-button
          >

          <el-button
            size="small "
            type="danger"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfservercar:quota:delete']"
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

    <!-- 添加或修改油卡管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车牌号" prop="licensePlate">
          <!-- <el-select
            v-model="form.licensePlate"
            placeholder="请选择车牌号"
            @change="handleChange"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in vehicleModelNamelist"
              :key="index"
              :label="item.licensePlate"
              :value="item.licensePlate"
            ></el-option>
          </el-select> -->
          <el-autocomplete
            class="inline-input"
            v-model="form.licensePlate"
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
        <el-form-item label="油品卡号">
          <el-input v-model="form.oilCardNumber" placeholder="请输入油品卡号" />
        </el-form-item>
        <el-form-item label="油品" prop="oils">
          <el-select
            v-model="form.oils"
            placeholder="请选择油品"
            @change="handleoilsChange"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in oilslist"
              :key="index"
              :label="item.dictLabel"
              :value="item.dictValue"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="办卡时间" prop="cardTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 100%"
            v-model="form.cardTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择办卡时间"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="到期时间" prop="dueTime">
          <el-date-picker
            clearable
            size="small"
            style="width: 100%"
            v-model="form.dueTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择到期时间"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="btn_loading" @click="submitForm"
          >确 定</el-button
        >
        <el-button @click="cancel">取 消</el-button>
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
            <a href="/oilCard.xlsx" download="油卡.xlsx">下载模板</a>
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
  OilCardList,
  addcarlist,
  addCard,
  updateCard,
  deleteCard,
  I01OilCardImport,
} from "@/api/card/oilCardManagement";
export default {
  name: "oilCardManagement",
  inject: ["reload"],
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
      // 油卡管理表格数据
      cardList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        // licensePlate: null,
        // oilCardNumber: null,
      },

      // 表单参数
      form: {
        licensePlate: null,
        oilCardNumber: null,
        oils: null,
        cardTime: null,
        dueTime: null,
        id: null,
      },

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
    this.getvehicleModelName();
    this.getoils();
    this.getList();
  },
  methods: {
    querySearch(queryString, cb) {
      var restaurants = this.vehicleModelNamelist;
      var results = queryString
        ? restaurants.filter(this.createFilter1(queryString))
        : restaurants;
      cb(results);
    },
    handleSelect(item) {
      console.log(item);
      // this.addform.licensePlate = item.licensePlate;
      // this.updateform.frameNumber = item.frameNumber;
      // this.updateform.id = item.id;
      console.log("item",item);
      this.form.licensePlate = item.licensePlate;
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
    // 是否标红
    tableRowClassName(row) {
      console.log(row.row.mathDays);
      switch (row.row.mathDays) {
        case 1:
          return "error-row";
          break;
        default:
          break;
      }
    },
    // 车辆列表
    getvehicleModelName() {
      addcarlist({}).then((res) => {
        this.vehicleModelNamelist = res.data;
      });
    },
    handleChange(val) {
      console.log(val, JSON.stringify(this.vehicleModelNamelist));
      for (const item of this.vehicleModelNamelist) {
        if (val == item.id) {
          console.log(item);
          console.log(item.licensePlate);
          this.form.licensePlate = item.licensePlate;
        }
      }
    },
    /** 查询油卡管理列表 */
    getList() {
      this.loading = true;
      OilCardList(this.queryParams).then((response) => {
        this.cardList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 油卡下拉
    getoils() {
      this.getDicts("f_oil_type").then((response) => {
        this.oilslist = response.data;
        console.log(this.oilslist);
      });
    },

    handleoilsChange(val) {
      console.log(val, JSON.stringify(this.oilslist));
      for (const item of this.oilslist) {
        if (val == item.id) {
          this.form.dictLabel = item.dictLabel;
        }
      }
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.getList();
      this.reset();
      console.log("取消");
      // this.reload();
    },
    // 表单重置
    reset() {
      this.form = {
        licensePlate: null,
        oilCardNumber: null,
        oils: null,
        cardTime: null,
        dueTime: null,
        id: null,
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
    // // 多选框选中数据
    // handleSelectionChange(selection) {
    //   this.ids = selection.map(item => item.id);
    //   this.single = selection.length !== 1;
    //   this.multiple = !selection.length;
    // },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加油卡管理";
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
        I01OilCardImport(fd)
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
          });
      } else {
        this.$message({
          message: "请导入文件",
        });
      }
    },
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      // console.log(row);
      this.reset();
      this.open = true;
      this.title = "修改油卡管理";
      this.form.licensePlate = row.licensePlate;
      this.form.oilCardNumber = row.oilCardNumber;
      this.form.oils = row.oils + "";
      this.form.cardTime = row.cardTime;
      this.form.dueTime = row.dueTime;
      this.form.id = row.id;
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        this.btn_loading = true;
        setTimeout(() => {
          this.btn_loading = false;
        }, 1000);
        if (valid) {
          if (this.form.id != null) {
            updateCard(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
            // this.reload();
          } else {
            addCard(this.form).then((response) => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
            // this.reload();

            // console.log(this.form);
          }
        }
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      const licensePlate = row.licensePlate;
      this.$confirm(
        '是否确认删除车牌号"' + licensePlate + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return deleteCard(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/card/export",
        {
          ...this.queryParams,
        },
        `system_card.xlsx`
      );
    },
  },
};
</script>
<style lang="scss">
.el-table .error-row {
  background: #ff4949;
}
</style>
