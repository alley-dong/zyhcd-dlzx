<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline" :model="listQuery">
      <el-form-item label="账号：">
        <el-input
          style="width: 150px"
          clearable
          v-model="listQuery.userName"
          placeholder="请输入账号"
        />
      </el-form-item>
      <el-form-item label="手机号码：">
        <el-input
          @input="phonenumberInput"
          style="width: 200px"
          clearable
          v-model="listQuery.phonenumber"
          placeholder="请输入手机号码"
        />
      </el-form-item>
      <el-form-item label="状态：">
        <el-select
          v-model="listQuery.status"
          clearable
          placeholder="状态"
          style="width: 120px"
        >
          <el-option
            v-for="item in status"
            :key="item.value"
            :value="item.value"
            :label="item.label"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间：">
        <el-date-picker
          style="width: 200px"
          type="date"
          @change="selectTimeChange"
          placeholder="选择日期"
          v-model="listQuery.cTime"
        ></el-date-picker>
      </el-form-item>
      <el-button
        type="primary"
        style="float: right; margin: 0 10px 20px 0"
        @click="addClick('1')"
        >新增</el-button
      >
      <el-button
        type="primary"
        style="float: right; margin: 0 10px 20px 0"
        @click="handleFilter"
        >查询</el-button
      >
    </el-form>
    <el-table v-loading="listLoading" border :data="tableData">
      <el-table-column
        type="index"
        :index="typeIndex"
        label="序号"
        align="center"
      ></el-table-column>
      <el-table-column label="账号" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="账号姓名" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.nickName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.phonenumber }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" width="100px">
        <template slot-scope="scope">
          <el-row>
            <el-col :span="24">
              <el-switch
                v-model="scope.row.status"
                active-color="#13ce66"
                inactive-color="#ccc"
                active-value="0"
                inactive-value="1"
                @change="handleSwitch(scope.$index, scope.row)"
              />
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="300px"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            @click="handleResetPwd(scope.row)"
            >重置密码</el-button
          >
          <el-button
            size="small"
            type="primary"
            @click="addClick('2', scope.row.userId)"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
            @click="deleteClick(scope.row.userId)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 新增编辑弹窗 -->
    <el-dialog
      :title="diaTitle"
      :visible.sync="dialogVisible"
      width="640px"
      :before-close="handleClose"
    >
      <el-form ref="form" :model="form" label-width="100px">
        <div class="dialog-form">
          <el-form-item label="账号姓名：" style="float: left" prop="nickName">
            <el-input
              v-model="form.nickName"
              placeholder="请输入账号姓名"
            ></el-input>
          </el-form-item>

          <el-form-item
            label="手机号码："
            style="float: left"
            prop="phonenumber"
          >
            <el-input
              v-model.number="form.phonenumber"
              placeholder="请输入手机号码"
            ></el-input>
          </el-form-item>
        </div>
        <div class="dialog-form">
          <el-form-item label="邮箱：" style="width: 290px" prop="email">
            <el-input
              v-model="form.email"
              placeholder="请输入邮箱地址"
            ></el-input>
          </el-form-item>
        </div>
        <div class="dialog-form" style="overflow: hidden">
          <el-form-item label="账号：" style="float: left" prop="userName">
            <el-input
              v-model="form.userName"
              placeholder="请输入账号"
            ></el-input>
          </el-form-item>
          <el-form-item
            v-if="isState !== 2"
            label="密码："
            style="float: left"
            prop="password"
          >
            <el-input
              type="password"
              v-model="form.password"
              placeholder="请输入密码"
            ></el-input>
          </el-form-item>
        </div>
        <div class="dialog-form" style="overflow: hidden">
          <el-form-item
            label="用户性别："
            style="float: left; width: 292px"
            prop="sex"
          >
            <el-select v-model="form.sex" placeholder="请选择用户性别">
              <el-option label="男" value="0"></el-option>
              <el-option label="女" value="1"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="状态：" style="float: left" prop="status">
            <el-radio-group v-model="form.status">
              <el-radio label="0">正常</el-radio>
              <el-radio label="1">停用</el-radio>
            </el-radio-group>
          </el-form-item>
        </div>
        <div class="dialog-form">
          <el-form-item
            label="角色："
            style="float: left; width: 292px"
            prop="roleIds"
          >
            <el-select
              v-model="form.roleIds"
              placeholder="请选择角色"
              multiple
              @change="roleChange"
            >
              <!-- multiple  -->
              <el-option
                v-for="item in roleOptions"
                :key="item.roleId"
                :label="item.roleName"
                :value="item.roleId"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item
            label="所属维修厂："
            label-width="120px"
            style="float: left; width: 292px"
            prop="garageName"
          >
            <el-select
              v-model="form.garageId"
              placeholder="请选择所谓维修厂"
              @change="garageChange"
            >
              <el-option
                v-for="item in B01ListCarDataList"
                :label="item.name"
                :value="item.id"
                :key="item.id"
              ></el-option>
            </el-select>
          </el-form-item>
        </div>
        <div>
          <el-form-item label="备注：" prop="remark">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>
        </div>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button
          v-if="showBtn"
          type="primary"
          :loading="saveLoading"
          @click="handleSaveAdd()"
          >确 定</el-button
        >
        <el-button
          v-else
          type="primary"
          :loading="saveLoading"
          @click="handleSaveEdit()"
          >确 定</el-button
        >
        <el-button @click="handleClose()">取 消</el-button>
      </span>
    </el-dialog>
    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.pageNum"
      :limit.sync="listQuery.pageSize"
      @pagination="getList"
    />
  </div>
</template>

<script>
import {
  accountManageList, // 列表
  accountManageDetails, // 详情
  accountEdit, // 编辑
  accountAdd, // 新增
  accountDelete, // 删除
  B01ListCar, // 维修厂下拉选
  changeStatus, //账号管理-账号启用/禁用
  B02ListTr,
} from "@/api/systemSettings/accountManage";
import { resetUserPwd, delUser } from "@/api/system/user";
export default {
  name: "accountManage",
  data() {
    return {
      isState: null, //是否为编辑
      roleId: null,
      total: 10,
      listLoading: false,
      tableData: [],
      B01ListCarDataList: [],
      saveLoading: false,
      roleOptions: [],
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        userName: "",
        phonenumber: "",
        status: null,
      },
      status: [
        {
          value: "0",
          label: "启动",
        },
        {
          value: "1",
          label: "禁用",
        },
      ],
      // 新增编辑弹窗
      dialogVisible: false,
      diaTitle: "添加用户",
      showBtn: true,

      form: {
        nickName: "",
        phonenumber: "",
        email: "",
        userName: "",
        password: "",
        sex: null,
        status: null,
        roleIds: [],
        garageName: null,
        garageId: null,
        remark: null,
      },
    };
  },
  created() {
    this.getList();
  },
  //格式化时间戳
  //   filters: {
  //     data(input) {
  //         console.log(this.settingTime);
  //     //    return this.settingTime(input)
  //     },
  //   },
  mounted() {
    this.init();
  },
  methods: {
    //   初始化
    init() {
      this.B01ListCarList();
      this.B02ListTrList();
    },
    //   时间格式化
    settingTime(val, isMin) {
      // 是否到分isMin
      const d = new Date(val);
      const year = d.getFullYear();
      const month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      const day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      const hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      const min = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      if (isMin) {
        return year + "-" + month + "-" + day;
      } else {
        return year + "-" + month + "-" + day + " " + hours + ":" + min;
      }
    },
    // 选择时间时
    selectTimeChange(val) {
      this.listQuery.cTime = this.settingTime(val, true);
    },
    // 点击新增和编辑按钮判断
    addClick(data, id) {
      if (data === "1") {
        this.isState = 1;
        this.dialogVisible = true;
        this.diaTitle = "添加用户";
        this.showBtn = true;
      } else if (data === "2") {
        this.isState = 2;
        this.dialogVisible = true;
        this.diaTitle = "编辑用户";
        this.showBtn = false;
        this.handleDetails(id);
      }
    },
    // 新增编辑弹窗关闭
    handleClose() {
      this.reset();
      this.dialogVisible = false;
      this.$refs["form"].resetFields();
    },
    // 编辑获取数据
    handleDetails(id) {
      accountManageDetails(id)
        .then((response) => {
          console.log(response.data);
          response.data.phonenumber = parseInt(response.data.phonenumber);
          this.form = response.data;
          this.form.roleIds = response.roleIds;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //切换状态按钮
    handleSwitch(index, row) {
      console.log(row);
      const param = {
        userId: row.userId,
        status: row.status,
      };
      changeStatus(param) //更新状态的接口
        .then((res) => {
          console.log(res);
          this.getList();
        })
        .catch(() => {
          this.getList();
        });
    },
    // 状态切换
    // changeStatus(){

    // },
    //显示序号
    typeIndex(index) {
      return (this.listQuery.pageNum - 1) * this.listQuery.pageSize + index + 1;
    },

    // 查询
    handleFilter() {
      this.listQuery.pageNum = 1;
      this.getList();
    },
    // 获取table的表格数据
    getList() {
      console.log(".11111是否被调用");
      this.listLoading = true;
      accountManageList(this.listQuery)
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
    // 重置
    reset() {
      this.form = {
        nickName: "",
        phonenumber: "",
        email: "",
        userName: "",
        password: "",
        sex: null,
        status: null,
        roleIds: [],
        garageName: null,
        garageId: null,
        remark: null,
      };
      this.roleId = null;
    },
    // 保存按钮loading
    handleLoading() {
      this.saveLoading = true;
      setTimeout(() => {
        this.saveLoading = false;
      }, 1000);
    },
    // 新增确定
    handleSaveAdd() {
      console.log(".this.form", this.form);
      this.$refs["form"].validate((valid) => {
        if (valid) {
          accountAdd(this.form)
            .then((response) => {
              console.log(".addClick", response);
              this.$message({
                message: "添加成功",
                type: "success",
              });
              this.dialogVisible = false;
              this.$refs["form"].resetFields();
              this.handleLoading();

              this.reset();
              this.getList();
            })
            .catch((err) => {
              this.listLoading = false;
              this.handleLoading();

              console.log(err);
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
          this.handleLoading();
          accountEdit(this.form)
            .then((response) => {
              console.log(".response", response);
              this.$message({
                message: "编辑成功",
                type: "success",
              });
              this.dialogVisible = false;
              this.$refs["form"].resetFields();
              this.reset();

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
      console.log(id);
      let that = this;
      that
        .$confirm("是否确认删除?", "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
        .then(function () {
          //   Id = JSON.parseInt(id);
          delUser(id)
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
    /** 重置密码按钮操作 */
    handleResetPwd(row) {
      this.$confirm(
        "您确定要重置该用户密码？重置后默认密码为123456！",
        "提示",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
        }
      )
        .then(() => {
          console.log("点击了确定", row);
          let data = {
            userId: row.userId,
            password: "123456",
          };
          resetUserPwd(data).then((response) => {
            this.msgSuccess("重置成功");
          });
        })
        .catch(() => {});
    },
    // 维修厂下拉选
    B01ListCarList() {
      B01ListCar().then((res) => {
        console.log(res);
        this.B01ListCarDataList = res.data;
      });
    },
    //角色下拉选
    B02ListTrList() {
      B02ListTr().then((res) => {
        console.log(res);
        this.roleOptions = res.data;
      });
    },
    // 维修厂选择事件
    garageChange(val) {
      for (const item of this.B01ListCarDataList) {
        if (item.id == val) {
          this.form.garageName = item.name;
        }
      }
    },
    // 手机号码输入
    phonenumberInput(val) {
      console.log(val);
    },
    // 角色选择
    roleChange(val) {
      console.log(val);
      // let arr = [];
      // arr.push(val);
      // this.form.roleIds = arr;
    },
  },
};
</script>

<style lang="scss" >
.dialog-form {
  .el-input {
    z-index: 99;
  }
}
.el-select__tags {
  z-index: 999;
}
</style>
