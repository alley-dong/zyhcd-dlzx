<template>
  <div class="app-container">
    <!--  -->
    <div style="overflow: auto">
      <div style="line-height: 36px; float: left">
        审批业务:{{ form.types }}
      </div>
      <el-button
        type="primary"
        style="float: right; margin: 0 20px 20px 0"
        @click="handleAdd"
        >新增审批级别</el-button
      >
      <el-button
        type="primary"
        style="float: right; margin: 0 20px 20px 0"
        @click="$router.go(-1)"
        >返回</el-button
      >
    </div>
    <div style="margin-bottom: 20px">审批流程：{{ level }}级审批</div>
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 100%"
    >
      <!-- <el-table-column
        type="index"
        :index="typeIndex"
        label="序号"
        align="center"
        width="80px"
      ></el-table-column> -->
      <el-table-column label="审批级别" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.level + "级审批" }}</span>
        </template>
      </el-table-column>
      <!-- TODO: -->

      <!-- TODO: -->
      <el-table-column label="审批角色" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="审批人" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.userName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="抄送角色" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.ccRoleName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="抄送人" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.ccUserName }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="状态" align="center" width="100px">
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
      </el-table-column> -->

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="300px"
      >
        <template slot-scope="scope">
          <!-- <el-button
            size="small"
            type="primary"
            @click="handleUpdate(scope.row)"
            >查看</el-button
          > -->
          <el-button
            size="small"
            type="primary"
            @click="handleUpdate(scope.row)"
            >编辑</el-button
          >
          <el-button
            v-if="scope.row.level !== 1"
            size="small"
            type="danger"
            style="margin-left: 20px"
            @click="deleteClick(scope.row)"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="listQuery.pageNum"
      :limit.sync="listQuery.pageSize"
      @pagination="getList"
    /> -->
    <!-- 添加或修改角色配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="审批角色" prop="roleId">
          <el-select
            v-model="form.roleId"
            placeholder="请选择审批角色"
            @change="roleChange($event, 'roleName')"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in roleOptions"
              :key="index"
              :label="item.roleName"
              :value="item.roleId"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- TODO: -->
        <el-form-item label="审批人员" prop="userId">
          <el-select
            v-model="form.userId"
            placeholder="请选择审批人员"
            @change="userChange($event, 'userName')"
            :disabled="!form.roleId"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in userOptions"
              :key="index"
              :label="item.nickName"
              :value="item.userId"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="抄送角色" prop="ccRoleId">
          <el-select
            v-model="form.ccRoleId"
            placeholder="请选择抄送角色"
            @change="roleChange($event, 'ccRoleName')"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in roleOptions"
              :key="index"
              :label="item.roleName"
              :value="item.roleId"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <!-- TODO: -->

        <el-form-item label="抄送人员" prop="ccUserId">
          <el-select
            v-model="form.ccUserId"
            placeholder="请选择抄送人员"
            @change="userChange($event, 'ccUserName')"
            clearable
            :disabled="!form.ccRoleId"
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in userOptions1"
              :key="index"
              :label="item.nickName"
              :value="item.userId"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" :loading="saveLoading"
          >确 定</el-button
        >
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  miniofileB05list,
  delUser,
  configB05UserRolelist,
  miniofileB05Add,
  miniofileB05EditSource,
  miniofileB05get
} from "@/api/system/approval";

import { B02ListTr } from "@/api/systemSettings/accountManage";
// import {
//   listRole,
//   getRole,
//   delRole,
//   addRole,
//   updateRole,
//   dataScope,
//   changeRoleStatus,
// } from "@/api/system/role";
// import {
//   treeselect as menuTreeselect,
//   B04roleMenuTreeselect,
// } from "@/api/system/menu";
// import {
//   treeselect as deptTreeselect,
//   roleDeptTreeselect,
// } from "@/api/system/dept";

export default {
  name: "roleManage",
  data() {
    return {
      total: 10,
      listLoading: false,
      saveLoading: false,
      tableData: [],
      level: undefined,
      // 弹出层标题
      title: "",
      type: null,
      // 是否显示弹出层
      open: false,
      listQuery: {
        pageNum: 1,
        pageSize: 10
        // roleName: "",
        // status: null,
        // cTime: "",
      },
      // 状态数据字典
      statusOptions: [],
      // 菜单列表
      menuOptions: [],
      // 部门列表
      deptOptions: [],
      roleOptions: [],
      userOptions: [],
      userOptions1: [],
      menuExpand: null,
      menuNodeAll: null,
      form: {
        type: null,
        configId: null,
        roleId: null,
        roleName: null,
        userId: null,
        userName: null,
        ccRoleId: null,
        ccRoleName: null,
        ccUserId: null,
        ccUserName: null
      },
      defaultProps: {
        children: "children",
        label: "label"
      },
      status: [
        {
          value: "0",
          label: "启动"
        },
        {
          value: "1",
          label: "禁用"
        }
      ],
      // 表单校验
      rules: {
        roleId: [
          { required: true, message: "审批角色不能为空", trigger: "blur" }
        ],
        // roleKey: [
        //   { required: true, message: "权限字符不能为空", trigger: "blur" }
        // ],
        roleSort: [
          { required: true, message: "角色顺序不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_normal_disable").then(response => {
      this.statusOptions = response.data;
    });
  },
  mounted() {
    this.form.types = this.$route.query.type;
    // this.form.type = this.$route.query.mytype;
    this.form.configId = this.$route.params.id;

    B02ListTr().then(res => {
      console.log(res);
      this.roleOptions = res.data;
    });
  },
  watch: {
    'form.ccRoleId': {
      //监听的对象
      deep: true, //深度监听设置为 true
      handler: function(newV, oldV) {
        console.log("watch中：", newV=='');
        if (newV==''){
          this.form.ccRoleId = null
          this.form.ccRoleName = null
        }
      }
    },
    'form.ccUserId': {
      //监听的对象
      deep: true, //深度监听设置为 true
      handler: function(newV, oldV) {
        if (newV==''){
          this.form.ccUserId = null
          this.form.ccUserName = null
        }
      }
    },
    'form.roleId': {
      //监听的对象
      deep: true, //深度监听设置为 true
      handler: function(newV, oldV) {
        if (newV==''){
          this.form.roleId = null
          this.form.roleName = null
        }
      }
    },
    'form.userId': {
      //监听的对象
      deep: true, //深度监听设置为 true
      handler: function(newV, oldV) {
        if (newV==''){
          this.form.userId = null
          this.form.userName = null
        }
      }
    }
    /* 
      this.form.ccRoleId += "";
          this.form.ccUserId += "";
          this.form.roleId += "";
          this.form.userId += "";
    */
    // form[ccRoleId](newValue, oldValue) {

    // }
  },
  //格式化时间戳
  filters: {
    data(input) {
      const d = new Date(input);
      const year = d.getFullYear();
      const month =
        d.getMonth() + 1 < 10 ? "0" + (d.getMonth() + 1) : d.getMonth() + 1;
      const day = d.getDate() < 10 ? "0" + d.getDate() : d.getDate();
      const hours = d.getHours() < 10 ? "0" + d.getHours() : d.getHours();
      const min = d.getMinutes() < 10 ? "0" + d.getMinutes() : d.getMinutes();
      const sec = d.getSeconds() < 10 ? "0" + d.getSeconds() : d.getSeconds();
      return year + "-" + month + "-" + day + " ";
    }
  },
  methods: {
    // 审批人员下拉
    configB05UserRoleDatalist(id, name) {
      configB05UserRolelist({ roleId: id }).then(res => {
        switch (name) {
          case "roleName":
            this.userOptions = res.data;
            break;
          case "ccRoleName":
            this.userOptions1 = res.data;
            break;
          default:
            break;
        }
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 角色选择事件
    roleChange(val, name) {
      console.log(val, name);
      for (const item of this.roleOptions) {
        if (val == item.roleId) {
          this.form[name] = item.roleName;
        }
      }
      switch (name) {
        case "roleName":
          this.configB05UserRoleDatalist(val, "roleName");
          this.form.userId = this.form.userName = null;
          break;
        case "ccRoleName":
          this.configB05UserRoleDatalist(val, "ccRoleName");
          this.form.ccUserId = this.form.ccUserName = null;
          break;

        default:
          break;
      }
    },
    // TODO:
    userChange(val, name) {
      let Options = name == "userName" ? this.userOptions : this.userOptions1;
      for (const item of Options) {
        if (val == item.userId) {
          console.log(item);
          this.form[name] = item.nickName;
        }
      }
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.saveLoading = true;
          this.form.type = this.$route.query.type1;

          // setTimeout(() => {
          //   this.saveLoading = false;
          // }, 2000);
          if (this.form.id != undefined) {
            // this.form.menuIds = this.getMenuAllCheckedKeys();
            miniofileB05EditSource(this.form)
              .then(response => {
                this.msgSuccess("修改成功");
                this.saveLoading = false;
                this.open = false;
                this.getList();
                this.reset();
              })
              .catch(err => {
                this.saveLoading = false;
              });
          } else {
            // this.form.menuIds = this.getMenuAllCheckedKeys();
            miniofileB05Add(this.form)
              .then(response => {
                this.msgSuccess("新增成功");
                this.saveLoading = false;
                this.open = false;
                this.getList();
                this.reset();
              })
              .catch(err => {
                this.saveLoading = false;
              });
          }
        }
      });
    },
    // 所有菜单节点数据
    getMenuAllCheckedKeys() {
      // 目前被选中的菜单节点
      let checkedKeys = this.$refs.menu.getCheckedKeys();
      // 半选中的菜单节点
      let halfCheckedKeys = this.$refs.menu.getHalfCheckedKeys();
      checkedKeys.unshift.apply(checkedKeys, halfCheckedKeys);
      return checkedKeys;
    },
    // 树权限（展开/折叠）
    handleCheckedTreeExpand(value, type) {
      if (type == "menu") {
        let treeList = this.menuOptions;
        for (let i = 0; i < treeList.length; i++) {
          this.$refs.menu.store.nodesMap[treeList[i].id].expanded = value;
        }
      } else if (type == "dept") {
        let treeList = this.deptOptions;
        for (let i = 0; i < treeList.length; i++) {
          this.$refs.dept.store.nodesMap[treeList[i].id].expanded = value;
        }
      }
    },
    // 树权限（全选/全不选）
    handleCheckedTreeNodeAll(value, type) {
      if (type == "menu") {
        this.$refs.menu.setCheckedNodes(value ? this.menuOptions : []);
      } else if (type == "dept") {
        this.$refs.dept.setCheckedNodes(value ? this.deptOptions : []);
      }
    },
    // 树权限（父子联动）
    handleCheckedTreeConnect(value, type) {
      if (type == "menu") {
        this.form.menuCheckStrictly = value ? true : false;
      } else if (type == "dept") {
        this.form.deptCheckStrictly = value ? true : false;
      }
    },
    /** 根据角色ID查询菜单树结构 */
    getRoleMenuTreeselect(roleId) {
      return B04roleMenuTreeselect(roleId).then(response => {
        this.menuOptions = response.menus;
        return response;
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.open = true;
      this.title = "添加";
    },
    // 表单重置
    reset() {
      this.form = {
        type: this.$route.query.mytype,
        configId: this.$route.params.id,
        roleId: null,
        roleName: null,
        userId: null,
        userName: null,
        ccRoleId: null,
        ccRoleName: null,
        ccUserId: null,
        ccUserName: null
      };
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      console.log("查看");
      this.open = true;
      this.title = "编辑";

      this.form = JSON.parse(JSON.stringify(row));
      console.log(row);
      this.configB05UserRoleDatalist(row.roleId, "roleName");
      this.configB05UserRoleDatalist(row.ccRoleId, "ccRoleName");
    },

    /** 查询菜单树结构 */
    getMenuTreeselect() {
      menuTreeselect().then(response => {
        console.log(response);
        this.menuOptions = response.data;
      });
    },
    //切换状态按钮
    handleSwitch(index, row) {
      console.log(row);
      const param = {
        roleId: row.roleId,
        status: row.status
      };
      changeRoleStatus(param) //更新状态的接口
        .then(res => {
          console.log(res);
          this.getList();
        })
        .catch(() => {});
    },
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
      this.listLoading = true;
      miniofileB05list({ configId: this.$route.params.id })
        .then(response => {
          console.log(response);
          this.tableData = response.data;
          this.listLoading = false;
          this.level = response.data.length;
        })
        .catch(err => {
          this.listLoading = false;
          console.log(err);
        });
    },
    //删除
    deleteClick(row) {
      const roleIds = row.roleId || this.ids;
      let _this = this;
      this.$confirm("是否确认删除", "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return miniofileB05get({
            id: row.id,
            configId: _this.$route.params.id,
            level: row.level,
            type: row.type
          });
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    }
  }
};
</script>

<style scoped></style>
