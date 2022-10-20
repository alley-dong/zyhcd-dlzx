<template>
  <div>
    <el-form
      :inline="true"
      class="demo-form-inline"
      :model="listQuery"
      style="width: 98%; margin: 0 auto; margin-top: 30px"
    >
      <div>
        <el-form-item label="姓名：">
          <el-input
            v-model="listQuery.name"
            placeholder="姓名"
            style="width: 150px"
          />
        </el-form-item>
        <el-form-item label="联系电话：">
          <el-input v-model="listQuery.contact" placeholder="联系电话" />
        </el-form-item>
        <el-form-item label="身份证：">
          <el-input v-model="listQuery.idCard" placeholder="身份证" />
        </el-form-item>
        <el-form-item label="考核状态：">
          <el-select v-model="listQuery.status" placeholder="考核状态">
            <el-option
              v-for="item in status"
              :key="item.value"
              :value="item.value"
              :label="item.label"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item style="float: right">
          <buttonGroup
            :addHasPermi="['pfserverperson:driver:add']"
            @handleFilter="handleFilter"
            @handleAdd="addClick()"
          />
        </el-form-item>
        <!-- <el-button
          type="primary"
          style="float: right; margin-right: 10px"
          @click="handleFilter"
          >查询</el-button 
        >-->
      </div>
      <!-- <div>
        <el-button
          type="primary"
          size="mini"
          style="float: right; margin: 0 10px 20px 0"
          @click="addClick()"
          >+新增</el-button
        >
      </div> -->
    </el-form>
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 98%; margin: 0 auto"
    >
      <el-table-column
        type="index"
        :index="typeIndex"
        label="序号"
        align="center"
      ></el-table-column>
      <el-table-column label="姓名" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column label="性别" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.sex === 0">男</span>
          <span v-else-if="scope.row.sex === 1">女</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="联系电话" align="center" width="150px">
        <template slot-scope="scope">
          <span>{{ scope.row.contact }}</span>
        </template>
      </el-table-column>
      <el-table-column label="身份证" align="center" width="200px">
        <template slot-scope="scope">
          <span>{{ scope.row.idCard }}</span>
        </template>
      </el-table-column>
      <el-table-column label="创建日期" align="center" width="100px">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime | data }}</span>
        </template>
      </el-table-column>
      <el-table-column label="考核状态" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.status === 1">进行中</span>
          <span v-else-if="scope.row.status === 2">未通过</span>
          <span v-else-if="scope.row.status === 3">已通过</span>
          <span v-else-if="scope.row.status === 4">储备</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        class-name="small-padding fixed-width"
        width="450px"
        align="center"
      >
        <template slot-scope="scope">
          <router-link
            :to="{
              path: `/basicInfo/${scope.row.id}`,
              query: {
                status: scope.row.status,
              },
            }"
          >
            <el-button
              size="small"
              style="margin-right: 10px; min-width: 60px"
              type="primary"
              v-hasPermi="['pfserverperson:driver:edit']"
              >基本信息</el-button
            >
            <!-- v-if="
                scope.row.status === 1 ||
                scope.row.status === 2 ||
                scope.row.status === 3 ||
                scope.row.status === 4
              " -->
          </router-link>
          <router-link
            v-if="scope.row.status == 1 && scope.row.interviewStatus !== 3"
            :to="{ path: `/basicInfoInterview/${scope.row.id}` }"
          >
            <el-button
              size="small"
              plain
              style="margin-right: 10px"
              type="primary"
              v-hasPermi="['pfserverperson:driver:interviewReview']"
              >面试上传</el-button
            >
          </router-link>
          <router-link
            v-if="scope.row.status == 1 && scope.row.examStatus !== 3"
            :to="{ path: `/basicInfoWrite/${scope.row.id}` }"
          >
            <el-button
              size="small"
              style="margin-right: 10px"
              plain
              type="primary"
              v-hasPermi="['pfserverperson:driver:writtenReview']"
              >笔试上传</el-button
            >
          </router-link>
          <router-link
            v-if="scope.row.status == 1 && scope.row.driveStatus !== 3"
            :to="{ path: `/basicInfoSem/${scope.row.id}` }"
          >
            <el-button
              size="small"
              plain
              style="margin-right: 10px"
              type="primary"
              v-hasPermi="['pfserverperson:driver:practiceReview']"
              >实操上传</el-button
            >
          </router-link>
          <el-button
            v-if="scope.row.status == 4"
            size="small"
            plain
            style="margin-right: 10px"
            type="success"
            @click="employeeClick(scope.row.id)"
            >录用</el-button
          >
          <el-button
            size="small"
            style="margin-right: 10px"
            type="danger"
            @click="tableDel(scope.row.id)"
            v-if="
              scope.row.status === 2 ||
              scope.row.status === 3 ||
              scope.row.status === 4
            "
            v-hasPermi="['pfserverperson:driver:delete']"
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
          label="姓名："
          prop="name"
          :rules="[{ required: true, message: '姓名为必填项' }]"
        >
          <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item
          label="性别："
          prop="sex"
          :rules="[{ required: true, message: '性别为必选项' }]"
        >
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option label="男" :value="0"></el-option>
            <el-option label="女" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item
          label="联系电话："
          prop="contact"
          :rules="[{ required: true, message: '联系电话为必填项' }]"
        >
          <el-input
            v-model="form.contact"
            placeholder="请输入联系电话"
          ></el-input>
        </el-form-item>
        <el-form-item
          label="身份证号："
          prop="idCard"
          :rules="[{ required: true, message: '身份证号为必填项' }]"
        >
          <el-input
            v-model="form.idCard"
            placeholder="请输入身份证号"
          ></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button
          type="primary"
          @click="handleSaveAdd()"
          :loading="saveLoading"
          >保 存</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {
  interviewManageList,
  interviewManageAdd,
  interviewManageDelete,
  A09Employment,
} from "@/api/driver/interviewManage";
import buttonGroup from "@/views/components/com/buttonGroup";
export default {
  name: "interviewManage",
  components: {
    buttonGroup,
  },
  watch: {
    // data(newValue, oldValue) {

    // }
    $route(val) {
      this.getList();
    },
  },
  data() {
    return {
      saveLoading: false,
      total: 10,
      listLoading: false,
      tableData: [],
      status: [
        {
          value: "1",
          label: "进行中",
        },
        {
          value: "2",
          label: "未通过",
        },
        // {
        //   value: "3",
        //   label: "已通过",
        // },
        {
          value: "4",
          label: "储备",
        },
      ],
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        name: "",
        contact: "",
        idCard: "",
        status: null,
      },
      // 新增编辑弹窗
      dialogVisible: false,
      diaTitle: "新增",
      form: {
        name: "",
        sex: null,
        contact: "",
        idCard: "",
      },
    };
  },
  filters: {
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month = d.getMonth() + 1;
      const day = (d.getDate() < 10 ? "0" : "") + d.getDate();
      return year + "-" + month + "-" + day;
    },
  },
  created() {
    this.getList();
  },
  methods: {
    employeeClick(id) {
      let that = this;

      A09Employment(id).then((res) => {
        that.$message({
          message: res.msg,
          type: "success",
        });
    this.getList();

      });
    },
    // 列表的删除

    tableDel(val) {
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          interviewManageDelete(val)
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
    //显示序号
    typeIndex(index) {
      return (this.listQuery.pageNum - 1) * this.listQuery.pageSize + index + 1;
    },
    // 新增
    // addClick() {
    //   this.$router.push({ path: "/addTrainingInfo" });
    // },
    // 查询
    handleFilter() {
      this.listQuery.pageNum = 1;
      this.getList();
    },
    // 点击新增按钮
    addClick() {
      this.dialogVisible = true;
      this.diaTitle = "新增";
    },
    // 新增编辑弹窗关闭
    handleClose() {
      this.dialogVisible = false;
      this.$refs["form"].resetFields();
    },
    // 新增保存
    handleSaveAdd() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          console.log(".this.form", this.form);
          this.saveLoading = true;
          // setTimeout(() => {
          //   this.saveLoading = false;
          // }, 2000);
          interviewManageAdd(this.form)
            .then((response) => {
              console.log(".addClick", response);
              this.$message({
                message: "添加成功",
                type: "success",
              });
              this.dialogVisible = false;
              this.saveLoading = false;

              this.$refs["form"].resetFields();
              this.getList();
            })
            .catch((err) => {
              this.listLoading = false;
              this.saveLoading = false;

              console.log(err);
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    // 获取table的表格数据
    getList() {
      this.listLoading = true;
      interviewManageList(this.listQuery)
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
    basicInfoClick(id) {
      console.log(id);
      educationExperienceList(this.listQuery)
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
  },
};
</script>

<style scoped>
</style>