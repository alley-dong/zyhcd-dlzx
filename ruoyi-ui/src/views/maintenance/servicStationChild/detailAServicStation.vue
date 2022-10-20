<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="120px"
    >
      <el-form-item label="名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <!-- <el-form-item label="维保类别" prop="type">
        <el-select
          v-model="queryParams.type"
          placeholder="请选择维保类别"
          clearable
          size="small"
        >
          <el-option
            v-for="item in option"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          />
        </el-select>
      </el-form-item> -->
      <!-- <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item> -->
      <buttonGroup
        @handleFilter="handleQuery"
        :addHasPermi="['pfservercar:garagerepairitem:add']"
        @handleAdd="handleClick"
        @handleBack="handleBack"
        @handleImportant="handleImportant"
        :importantHasPermi="['pfservercar:garagerepairitem:import']"

        backButton
        leadingIn
        style="float: right"
      />
    </el-form>

    <el-table
      v-loading="loading"
      :data="repairList"
      @selection-change="handleSelectionChange"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <el-table-column label="车型" align="center" prop="vehicleModelName" />
      <el-table-column label="名称" align="center" prop="name" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="单价（元）" align="center" prop="unitPrice" />
      <el-table-column label="工时数" align="center" prop="duration" />

      <!-- <el-table-column label="单价（元）" align="center" prop="isFixed">
        <template slot-scope="scope">
          {{ scope.row.type == 1 ? "是" : "否" }}
        </template>
      </el-table-column> -->

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfservercar:garagerepairitem:editsave']"
            plain
            type="primary"
            >编辑</el-button
          >
          <!-- <el-button
            size="small"
            @click="detailClick(scope.row)"
            v-hasPermi="['system:repair:edit']"
            plain
            type="success"
            >维修名细</el-button
          > -->
          <el-button
            size="small"
            type="danger"
            @click="handleRemove(scope.row)"
            v-hasPermi="['pfservercar:garagerepairitem:delete']"
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

    <!-- 添加或修改维修保养-维修保养单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="车型" prop="vehicleModel">
          <el-select
            v-model="form.vehicleModel"
            placeholder="请选择车型"
            @change="handleChange"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="item in option"
              :key="item.id"
              :label="item.name"
              :value="item.id"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入名称" clearable />
        </el-form-item>
        <el-form-item label="单位" prop="unit">
          <el-input v-model="form.unit" placeholder="请输入单位" clearable />
        </el-form-item>
        <el-form-item label="单价(元)" prop="unitPrice">
          <el-input
            type="number"
            v-model="form.unitPrice"
            placeholder="请输入单价"
            clearable
          />
        </el-form-item>
        <el-form-item label="工时数" prop="duration">
          <el-input
            v-model="form.duration"
            placeholder="请输入工时数"
            type="number"
            clearable
          />
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
      title=" 导入"
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
            <!-- 下载模板 -->
            <a href=".././servingStation.xls" download="下载模板">下载模板</a>
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
// import { listRepair, getRepair, delRepair, addRepair, updateRepair } from "@/api/system/repair";
import {
  garageRepairItemH16list,
  commonSelectBoxD02CvhicleSelectBox, //下拉
  queryRepairSelectBox,//下拉
  garageRepairItemH16Add,
  garageRepairItemH16Info,
  garageRepairItemH16EditSave,
  garageRepairItemH16Delete,
  //   garageRepairItemH16Template,
  UploadFiles
} from "@/api/maintenance";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "Repair",
  components: {
    buttonGroup
  },
  data() {
    return {
      btn_loading: false,
      dialogVisible1: false,
      upfile: null,
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
      // 维修保养-维修保养单表格数据
      repairList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        garageId: undefined,
        pageNum: 1,
        pageSize: 10,
        name: null
      },
      option: [
        // {
        //   label: "全部",
        //   value: null,
        // },
        {
          label: "是",
          value: 1
        },
        {
          label: "否",
          value: 2
        }
      ],
      // 表单参数
      form: {
        garageId: null,
        name: null,
        vehicleModel: null,
        unit: null,
        unitPrice: null,
        duration: null,
        vehicleModelName: null //车辆名
      },
      // 表单校验
      rules: {}
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    this.queryParams.garageId = this.form.garageId = this.$route.params.id;
    queryRepairSelectBox({}).then(res => {
      this.option = res.data;
    });
  },
  filters: {
    repairStatus: function(value) {
      switch (value) {
        case 1:
          return "未开始";
          break;
        case 2:
          return "进行中";
          break;
        case 3:
          return "已完成";
          break;

        default:
          break;
      }
    },
    status: function(value) {
      switch (value) {
        case 1:
          return "待审核";
          break;
        case 2:
          return "已通过";
          break;
        case 3:
          return "未通过";
          break;
        case 4:
          return "已确认";
          break;
        default:
          break;
      }
    }
  },
  methods: {
    //文件提交之前的操作 可以进行type类型的判断
    onBeforeUploadImage(file) {
      console.log(file);
    },
    /** 查询维修保养-维修保养单列表 */
    getList() {
      this.loading = true;
      garageRepairItemH16list(this.queryParams).then(response => {
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 选择
    handleChange(val) {
      for (const item of this.option) {
        if (item.id == val) {
          this.form.vehicleModelName = item.name;
        }
      }
    },
    // 返回
    handleBack() {
      this.$router.go(-1);
    },
    handleImportant() {
      console.log("导入");
      this.dialogVisible1 = true;
    },
    // 导入弹窗关闭
    handleClose1() {
      this.dialogVisible1 = false;
    },
    // 明细
    detailClick(row) {
      console.log(row, "明细");
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    handleClick() {
      console.log("新增");
      this.open = true;
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        garageId: this.$route.params.id,
        name: null,
        vehicleModel: null,
        unit: null,
        unitPrice: null,
        duration: null,
        vehicleModelName: null //车辆名
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
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加维修保养-维修保养单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      // 审批
      // this.reset();
      this.open = true;
      const id = row.id || this.ids;
      garageRepairItemH16Info(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改维修保养-维修保养单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.btn_loading = true;
          setTimeout(() => {
            this.btn_loading = false;
          }, 1000);
          if (this.form.id != null) {
            garageRepairItemH16EditSave(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.reset();
            });
          } else {
            garageRepairItemH16Add(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
              this.reset();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleRemove(row) {
      const id = row.id || this.ids;
      // garageRepairItemH16Delete(id).then((res) => {
      //   this.getList();
      // });
      this.$confirm("是否确认删除", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return garageRepairItemH16Delete(id);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
      // this.$router.push("/lookUp/" + id);
      // console.log("查看");
    },
    uploadClick() {
      if (this.upfile) {
        console.log(this.upfile);
        let fd = new FormData();
        fd.append("file", this.upfile); // 要提交给后台的文件
        // fd.append("garagId", this.$route.params.id); // 要提交给后台的文件
        // console.log('fd',fd);
        // debugger
        // let obj = {
        //    fd,
        //     garagId:this.$route.params.id
        // }
        UploadFiles(fd, { garageId: this.$route.params.id })
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
            this.getList();
          });
      } else {
    this.getList();
      }
    },
    // downloadClick() {
    //     garageRepairItemH16Template().then(res=>{
    //         console.log(res);
    //     })
    // //   window.open(
    // //     `http://localhost/dev-api/pfserver-person/car/GarageRepairItem/H16Template`
    // //   );
    // },
    UploadFile(param) {
      this.upfile = param.file;
      console.log(this.upfile);
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/repair/export",
        {
          ...this.queryParams
        },
        `system_repair.xlsx`
      );
    }
  }
};
</script>
