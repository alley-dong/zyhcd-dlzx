<template>
  <div>
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <el-form-item label="请假类型：">
        <el-input v-model="listQuery.name" placeholder="请假类型" />
      </el-form-item>
      <el-form-item label="是否有薪：">
        <el-select v-model="listQuery.isSalary" placeholder="请选择是否有薪">
          <el-option
            v-for="item in isSalary"
            :key="item.value"
            :value="item.value"
            :label="item.label"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="员工类型：">
        <el-select v-model="listQuery.staffType" placeholder="请选择员工类型">
          <el-option
            v-for="item in staffType"
            :key="item.value"
            :value="item.value"
            :label="item.label"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <!-- <el-button type="primary" style="float: right; margin:0 10px 20px 0;" @click="addClick('1')">新增</el-button>
            <el-button type="primary" style="float: right; margin:0 10px 20px 0;" @click="handleFilter">查询</el-button> -->
      <buttonGroup
        @handleFilter="handleFilter"
        style="float: right"
        @handleAdd="addClick('1')"
        :addHasPermi="['pfserverperson:leavetype:add']"
      />
    </el-form>
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column label="员工类型" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.staffType === 1">正式工</span>
          <span v-else-if="scope.row.staffType === 2">外包工</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="请假类型" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否有薪" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.isSalary === 0">是</span>
          <span v-else-if="scope.row.isSalary === 1">否</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            plain
            type="primary"
            @click="addClick('2', scope.row.id)"
            style="margin-right: 15px"
            v-hasPermi="['pfserverperson:leavetype:edit']"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="deleteClick(scope.row.id)"
            v-hasPermi="['pfserverperson:leavetype:delete']"
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
          label="员工类型："
          prop="staffType"
          :rules="[{ required: true, message: '员工类型为必选项' }]"
        >
          <el-select v-model="form.staffType" placeholder="请选择员工类型">
            <el-option
              v-for="item in staffType1"
              :key="item.value"
              :value="item.value"
              :label="item.label"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="请假类型："
          prop="name"
          :rules="[{ required: true, message: '请假类型为必填项' }]"
        >
          <el-input
            v-model.number="form.name"
            placeholder="请输入请假类型"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="是否有薪："
          prop="isSalary"
          :rules="[{ required: true, message: '是否有薪为必选项' }]"
        >
          <el-select v-model="form.isSalary" placeholder="请选择是否有薪">
            <el-option
              v-for="item in isSalary1"
              :key="item.value"
              :value="item.value"
              :label="item.label"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button v-if="showBtn" :loading="saveLoading" type="primary" @click="handleSaveAdd()"
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
  leaveTypeManageList,
  leaveTypeManageDelete,
  leaveTypeManageDetails,
  leaveTypeManageEdit,
  leaveTypeManageAdd,
} from "@/api/driver/leaveTypeManage";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "leaveTypeManage",
  components: {
    buttonGroup,
  },
  data() {
    return {
      total: 10,
      saveLoading: false,
      isSalary: [
        {
          value: 0,
          label: "是",
        },
        {
          value: 1,
          label: "否",
        },
        {
          value: null,
          label: "全部",
        },
      ],
      staffType: [
        {
          value: 1,
          label: "正式工",
        },
        {
          value: 2,
          label: "外包工",
        },
        {
          value: null,
          label: "全部",
        },
      ],
      isSalary1: [
        {
          value: 0,
          label: "是",
        },
        {
          value: 1,
          label: "否",
        },
      ],
      staffType1: [
        {
          value: 1,
          label: "正式工",
        },
        {
          value: 2,
          label: "外包工",
        },
      ],
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        type: "",
        salary: null,
        straffType: null,
      },
      listLoading: false,
      tableData: [],
      // 新增编辑弹窗
      dialogVisible: false,
      diaTitle: "新增",
      showBtn: true,
      form: {
        staffType: null,
        name: "",
        isSalary: null,
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
      console.log(".11111是否被调用");
      this.listLoading = true;
      leaveTypeManageList(this.listQuery)
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
      leaveTypeManageDetails(id)
        .then((response) => {
          console.log(response);
          this.form = response.data;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    // 新增保存
    handleSaveAdd() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.saveLoading = true;
          setTimeout(() => {
            this.saveLoading = false;
          }, 1000);
          console.log(".this.form", this.form);
          leaveTypeManageAdd(this.form)
            .then((response) => {
              console.log(".addClick", response);
              this.$message({
                message: "添加成功",
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
    // 编辑保存
    handleSaveEdit() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          leaveTypeManageEdit(this.form)
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
          leaveTypeManageDelete(id)
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