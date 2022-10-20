<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="开始时间" prop="startTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.startTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择开始时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="finshTime">
        <el-date-picker
          clearable
          size="small"
          style="width: 200px"
          v-model="queryParams.finshTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择结束时间"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['pfservercar:vehicleInspectRecord:add']"
          >新增</el-button
        >
        <el-button type="primary" size="mini" @click="$router.go(-1)"
          >返回</el-button
        >
        <!-- <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button -->
        <!-- > -->
      </el-form-item>
    </el-form>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['pfservercar:vehicleInspectRecord:add']"
          >新增</el-button
        >
      </el-col> -->
    <!-- <el-col :span="1.5">
        <el-button
          type="success"
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['pfservercar:vehicleInspectRecord:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['pfservercar:vehicleInspectRecord:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['pfservercar:vehicleInspectRecord:export']"
          >导出</el-button
        >
      </el-col> -->
    <!-- <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar> -->
    <!-- </el-row> -->

    <el-table
      v-loading="loading"
      :data="vehicleInspectRecordList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>

      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column
        label="检车日期"
        align="center"
        prop="inspectDate"
        width="130"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.inspectDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="注册日期"
        align="center"
        prop="registerDate"
        width="130"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.registerDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="下次检车日期"
        align="center"
        prop="nextInspectDate"
        width="130"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.nextInspectDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="车辆年检费用"
        align="center"
        prop="vehiceInspectCost"
        width="80"
      />
      <el-table-column
        label="运营年检费用"
        align="center"
        prop="operateInspectCost"
        width="80"
      />
      <el-table-column
        label="复检费用"
        align="center"
        width="80"
        prop="reviewCost"
      />
      <el-table-column
        label="合计费用"
        align="center"
        width="80"
        prop="totalCost"
      />
      <el-table-column label="备注" align="center" width="80" prop="remarks" />
      <el-table-column
        label="附件"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-image
          v-if="scope.row.fileList[0]"
            style="width: 100px; height: 100px"
            :src="scope.row.fileList[0]"
            fit="contain"
            :preview-src-list="scope.row.fileList"
          ></el-image>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="150"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            @click="handleUpdate(scope.row)"
            type="primary"
            plain
            v-hasPermi="['pfservercar:vehicleInspectRecord:edit']"
            >修改</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfservercar:vehicleInspectRecord:delete']"
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

    <!-- 添加或修改车辆管理-车辆年检记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <!-- <el-form-item label="车辆id" prop="id">
          <el-input v-model="form.id" placeholder="请输入车辆id" />
        </el-form-item> -->
        <el-form-item label="车牌号" prop="licensePlate">
          <el-input
            disabled
            v-model="form.licensePlate"
            placeholder="请输入车牌号"
          />
        </el-form-item>

        <el-form-item label="注册日期" prop="registerDate">
          <el-date-picker
            clearable
            disabled
            :style="{ width: '100%' }"
            v-model="form.registerDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择注册日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检车日期" prop="inspectDate">
          <el-date-picker
            clearable
            :style="{ width: '100%' }"
            v-model="form.inspectDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择检车日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="下次检车日期" prop="nextInspectDate">
          <el-date-picker
            clearable
            :style="{ width: '100%' }"
            v-model="form.nextInspectDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择下次检车日期"
          >
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车辆年检费用" prop="vehiceInspectCost">
          <el-input
            v-model="form.vehiceInspectCost"
            placeholder="请输入车辆年检费用"
          />
        </el-form-item>
        <el-form-item label="运营年检费用" prop="operateInspectCost">
          <el-input
            v-model="form.operateInspectCost"
            placeholder="请输入运营年检费用"
          />
        </el-form-item>
        <el-form-item label="复检费用" prop="reviewCost">
          <el-input v-model="form.reviewCost" placeholder="请输入复检费用" />
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" placeholder="请输入备注" />
        </el-form-item>
        <!-- <el-form-item label="删除状态 0删除 1未删除" prop="deleteFlag">
          <el-input v-model="form.deleteFlag" placeholder="请输入删除状态 0删除 1未删除" />
        </el-form-item> -->
        <el-form-item label="导入文件">
          <div class="aaa_upload">
            <el-upload
              drag
              ref="upload"
              class="upload-btn"
              accept=".jpg, .png"
              :http-request="UploadFile"
              :action="uploadAction"
              :before-remove="handleRemove"
              :file-list="fileList"
              multiple
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">
                将文件拖到此处，或<em>点击上传</em>
              </div>
              <div class="el-upload__tip" slot="tip">
                只支持jpg、png照片格式
                <!-- <a href="/carTemplate.xls" download="下载模板.xls">下载模板</a> -->
              </div>
            </el-upload>
          </div>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" :loading="btn_loading" @click="submitForm"
          >确 定</el-button
        >
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
// import { listVehicleInspectRecord, getVehicleInspectRecord, delVehicleInspectRecord, addVehicleInspectRecord, updateVehicleInspectRecord } from "@/api/pfservercar/vehicleInspectRecord";
import {
  vehicleInspectRecordInfoList,
  vehicleInspectRecordAdd,
  vehicleInspectRecordEdit,
  vehicleInspectRecordDelete,
  vehicleInspectRecordEditSave,
} from "@/api/car/vehicle";
import { UploadFiles } from "@/api/upLoad/persomUpload";
export default {
  name: "VehicleInspectRecord",
  data() {
    return {
      url:
        "https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg",
      srcList: [
        "https://fuss10.elemecdn.com/8/27/f01c15bb73e1ef3793e64e6b7bbccjpeg.jpeg",
        "https://fuss10.elemecdn.com/1/8e/aeffeb4de74e2fde4bd74fc7b4486jpeg.jpeg",
      ],
      uploadAction: "https://jsonplaceholder.typicode.com/posts/",
      fileList: [],
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
      // 车辆管理-车辆年检记录表格数据
      vehicleInspectRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        inspectDate: null,
        startTime: undefined,
        finshTime: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  created() {
    this.getList();
    this.reset();
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(this.fileList);
      for (const [index, item] of this.fileList.entries()) {
        if (item.name == file.name) {
          console.log(index);
          this.fileList.splice(index, 1);
        }
      }
    },
    UploadFile(param) {
      // this.upfile = param.file;TODO:拿到图片
      // console.log(this.upfile);
      let fd = new FormData();
      console.log("param.file", param.file);
      fd.append("file", param.file); // 要提交给后台的文件
      UploadFiles(fd)
        .then((response) => {
          // {url:item,name:item.substring(item.lastIndexOf('/')+1)}
          let item = response.msg
          // console.log(response.msg.substring(0,str.lastIndexOf('.')));
          this.fileList.push({url:item,name:item.substring(item.lastIndexOf('/')+1)});
          // this.formData = JSON.parse(JSON.stringify(this.formData));
        })
        .catch((err) => {
          console.log(err);
        });
    },
    /** 查询车辆管理-车辆年检记录列表 */
    getList() {
      this.loading = true;
      this.queryParams.truckId = this.$route.params.id;
      vehicleInspectRecordInfoList(this.queryParams).then((response) => {
        for (const item of response.rows) {
          if (item.files) {
            item.fileList = item.files.split(',')
          }else{
            item.fileList = []
          }
        }
        this.vehicleInspectRecordList = response.rows;
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
      let { licensePlate, registerDate } = this.$route.query;
      this.fileList = [];
      this.form = {
        id: null,
        licensePlate,
        inspectDate: null,
        vehiceInspectCost: null,
        operateInspectCost: null,
        reviewCost: null,
        remarks: null,
        registerDate,
        nextInspectDate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null,
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
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加车辆管理-车辆年检记录";
      this.form.id = null;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      vehicleInspectRecordEdit(id).then((response) => {
        this.form = response.data;
        this.fileList = []
        if (response.data.files) {
          for (const item of response.data.files.split(',')) {
            this.fileList.push({url:item,name:item.substring(item.lastIndexOf('/')+1)})
            // console.log({url:item});
          }
          // this.fileList = response.data.files.split(',')
        }
        this.open = true;
        this.title = "修改车辆管理-车辆年检记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        this.btn_loading = true;
        setTimeout(() => {
          this.btn_loading = false;
        }, 1000);
        console.log(this.fileList);
        let str = ''
        for (const item of this.fileList) {
          console.log();
          str+=item.url+','
        }
        this.form.files = str.substring(0,str.length-1)
        console.log(this.form.files);
        if (valid) {
          if (this.form.id != null) {
            vehicleInspectRecordEditSave(this.form).then((response) => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
              this.form.id = null;
            });
          } else {
            this.form.id = this.$route.params.id;

            vehicleInspectRecordAdd(this.form)
              .then((response) => {
                this.msgSuccess("新增成功");
                this.open = false;
                this.getList();
                this.form.id = null;
              })
              .catch((err) => {
                this.form.id = null;
              });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm("是否确认删除", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(function () {
          return vehicleInspectRecordDelete(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "pfservercar/vehicleInspectRecord/export",
        {
          ...this.queryParams,
        },
        `pfservercar_vehicleInspectRecord.xlsx`
      );
    },
  },
};
</script>
<style lang="scss">
.aaa_upload {
  .el-upload-dragger {
    width: 340px;
  }
  .el-upload__tip {
    text-align: center;
  }
}
</style>