<template>
  <div>
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <el-form-item label="驾驶员类型：">
        <el-input v-model="listQuery.name" placeholder="驾驶员类型" />
      </el-form-item>
      <!-- <el-button type="primary" style="float: right;margin-right: 10px;" @click="handleFilter">查询</el-button>
            <el-button type="primary" style="float: right;margin-right: 10px;" @click="addClick('1')">新增</el-button> -->
      <buttonGroup
        :addHasPermi="['person:driverType:add']"
        @handleFilter="handleFilter"
        @handleAdd="addClick('1')"
        style="float: right"
      />
    </el-form>
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column label="驾驶员类型" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="长途/短途" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.type === 1">长途</span>
          <span v-else-if="scope.row.type === 2">短途</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="底薪(元)" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.basicSalary }}</span>
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
            size="small"
            plain
            @click="addClick('2', scope.row.id)"
            style="margin-right: 15px"
            v-hasPermi="['person:driverType:edit']"
            >编辑</el-button
          >
          <el-button
            v-if="scope.row.deleteType != 1"
            type="danger"
            size="small"
            @click="deleteClick(scope.row.id)"
            v-hasPermi="['person:driverType:remove']"
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
          label="驾驶员类型"
          prop="name"
          :rules="[
            { required: true, message: '驾驶员类型不能为空' },
            { type: 'string', message: '驾驶员类型必须为字符串型' },
          ]"
        >
          <el-input
            v-model="form.name"
            placeholder="请输入驾驶员类型"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="长途/短途"
          prop="type"
          :rules="[{ required: true, message: '长途/短途为必填项' }]"
        >
          <el-radio-group v-model="form.type">
            <el-radio :label="1">长途</el-radio>
            <el-radio :label="2">短途</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item
          label="底薪金额"
          prop="basicSalary"
          :rules="[
            { required: true, message: '底薪金额不能为空' },
            { type: 'number', message: '底薪金额必须为数字值' },
          ]"
        >
          <el-input
            v-model.number="form.basicSalary"
            placeholder="请输入底薪金额"
          ></el-input>
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
        <el-button v-else type="primary" @click="handleSaveEdit()"
          >保 存</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  driverTypeList,
  driverTypeAdd,
  driverTypeEdit,
  driverTypeDelete,
  driverTypeDetails,
} from "@/api/driver/driverTypeManage";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "driverTypeManage",
  components: {
    buttonGroup,
  },
  data() {
    return {
      total: 10,
      btn_loading: false,
      listLoading: false,
      // 列表数据
      tableData: [],
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: "",
      },
      // 新增编辑弹窗
      dialogVisible: false,
      diaTitle: "新增",
      showBtn: true,
      form: {
        name: "",
        type: null,
        basicSalary: null,
      },
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 查询
    handleFilter() {
      this.listQuery.pageNum = 1;
      this.getList();
    },
    // 新增编辑弹窗关闭
    handleClose() {
      this.dialogVisible = false;
      this.$refs["form"].resetFields();
    },
    // 获取table的表格数据
    getList() {
      this.listLoading = true;
      driverTypeList(this.listQuery)
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
    // 点击新增和编辑按钮判断
    addClick(data, id) {
      if (data === "1") {
        this.dialogVisible = true;
        this.diaTitle = "新增";
        this.showBtn = true;
      } else if (data === "2") {
        this.dialogVisible = true;
        this.diaTitle = "编辑";
        this.showBtn = false;
        this.handleDetails(id);
      }
    },
    // 编辑获取数据
    handleDetails(id) {
      driverTypeDetails(id)
        .then((response) => {
          console.log(response);
          this.form = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 新增确定
    handleSaveAdd() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          console.log(".this.form", this.form);
          this.btn_loading = true;
          driverTypeAdd(this.form)
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
    // 编辑确定
    handleSaveEdit() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          driverTypeEdit(this.form)
            .then((response) => {
              console.log(".response", response);
              this.$message({
                message: "编辑成功",
                type: "success",
              });
              this.dialogVisible = false;
              this.$refs["form"].resetFields();
              this.getList();
            })
            .catch((err) => {
              this.listLoading = false;
              console.log(err);
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
          driverTypeDelete(id)
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
</style>