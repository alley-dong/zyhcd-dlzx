<template>
  <div class="app-container">
    <el-form :inline="true" class="demo-form-inline" :model="listQuery">
      <el-form-item label="角色名称：">
        <el-input v-model="listQuery.roleName" clearable placeholder="请输入角色名称" />
      </el-form-item>
      <el-form-item label="状态：">
        <el-select v-model="listQuery.status" clearable placeholder="状态">
          <el-option
            v-for="item in status"
            :key="item.value"
            :value="item.value"
            :label="item.label"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="创建时间：">
        <el-date-picker
          type="date"
          placeholder="选择日期"
            value-format="yyyy-MM-dd"

          v-model="listQuery.cTime"
        ></el-date-picker>
      </el-form-item> -->
      <el-button
        type="primary"
        style="float: right; margin: 0 20px 20px 0"
        @click="handleAdd"
        >新增</el-button
      >
      <el-button
        type="primary"
        style="float: right; margin: 0 20px 20px 0"
        @click="handleFilter"
        >查询</el-button
      >
    </el-form>
    <el-table
      v-loading="listLoading"
      border
      :data="tableData"
      style="width: 100%"
    >
      <el-table-column
        type="index"
        :index="typeIndex"
        label="序号"
        align="center"
        width="80px"
      ></el-table-column>
      <el-table-column label="角色名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.roleName }}</span>
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
          <span>{{ scope.row.createTime }}</span> </template
        >
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
            @click="handleUpdate(scope.row)"
            >编辑</el-button
          >
          <el-button
            size="small"
            type="danger"
            style="margin-left: 20px"
            @click="deleteClick(scope.row)"
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
    <!-- 添加或修改角色配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!-- <el-form-item label="角色名称" prop="roleName"> -->
        <el-form-item label="角色名称">
          <el-input v-model="form.roleName" placeholder="请输入角色名称" />
        </el-form-item>
        <!-- <el-form-item label="权限字符" prop="roleKey">
          <el-input v-model="form.roleKey" placeholder="请输入权限字符" />
        </el-form-item>
        <el-form-item label="角色顺序" prop="roleSort">
          <el-input-number v-model="form.roleSort" controls-position="right" :min="0" />
        </el-form-item> -->
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in statusOptions"
              :key="dict.dictValue"
              :label="dict.dictValue"
              >{{ dict.dictLabel }}</el-radio
            >
          </el-radio-group>
        </el-form-item>
        <el-form-item label="菜单权限">
          <el-checkbox
            v-model="menuExpand"
            @change="handleCheckedTreeExpand($event, 'menu')"
            >展开/折叠</el-checkbox
          >
          <el-checkbox
            v-model="menuNodeAll"
            @change="handleCheckedTreeNodeAll($event, 'menu')"
            >全选/全不选</el-checkbox
          >
          <el-checkbox
            v-model="form.menuCheckStrictly"
            @change="handleCheckedTreeConnect($event, 'menu')"
            >父子联动</el-checkbox
          >
          <el-tree
            class="tree-border"
            :data="menuOptions"
            show-checkbox
            ref="menu"
            node-key="id"
            :check-strictly="!form.menuCheckStrictly"
            empty-text="加载中，请稍后"
            :props="defaultProps"
          ></el-tree>
        </el-form-item>
        <el-form-item label="备注">
          <el-input
            v-model="form.remark"
            type="textarea"
            placeholder="请输入内容"
          ></el-input>
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
import { roleManageList, delUser } from "@/api/systemSettings/roleManage";
import {
  listRole,
  getRole,
  delRole,
  addRole,
  updateRole,
  dataScope,
  changeRoleStatus,
} from "@/api/system/role";
import {
  treeselect as menuTreeselect,
  B04roleMenuTreeselect,
} from "@/api/system/menu";
import {
  treeselect as deptTreeselect,
  roleDeptTreeselect,
} from "@/api/system/dept";

export default {
  name: "roleManage",
  data() {
    return {
      total: 10,
      listLoading: false,
      saveLoading: false,
      tableData: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      listQuery: {
        pageNum: 1,
        pageSize: 10,
        roleName: "",
        status: null,
        cTime: "",
      },
      // 状态数据字典
      statusOptions: [],
      // 菜单列表
      menuOptions: [],
      // 部门列表
      deptOptions: [],
      menuExpand: null,
      menuNodeAll: null,
      form: {
        roleName: null,
        status: null,
        menuCheckStrictly: true,
        deptCheckStrictly: true,
        remark: null,
      },
      defaultProps: {
        children: "children",
        label: "label",
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
      // 表单校验
      rules: {
        roleName: [
          { required: true, message: "角色名称不能为空", trigger: "blur" },
        ],
        // roleKey: [
        //   { required: true, message: "权限字符不能为空", trigger: "blur" }
        // ],
        roleSort: [
          { required: true, message: "角色顺序不能为空", trigger: "blur" },
        ],
      },
    };
  },
  created() {
    this.getList();
    this.getDicts("sys_normal_disable").then((response) => {
      this.statusOptions = response.data;
    });
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
      return (
        year + "-" + month + "-" + day + " " 
      );
    },
  },
  methods: {
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    /** 提交按钮 */
    submitForm: function () {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.saveLoading = true;

          // setTimeout(() => {
          //   this.saveLoading = false;
          // }, 2000);
          if (this.form.roleId != undefined) {
            this.form.menuIds = this.getMenuAllCheckedKeys();
            updateRole(this.form)
              .then((response) => {
                this.msgSuccess("修改成功");
                this.saveLoading = false;
                this.open = false;
                this.getList();
              })
              .catch((err) => {
                this.saveLoading = false;
              });
          } else {
            this.form.menuIds = this.getMenuAllCheckedKeys();
            addRole(this.form)
              .then((response) => {
                this.msgSuccess("新增成功");
                this.saveLoading = false;
                this.open = false;
                this.getList();
              })
              .catch((err) => {
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
      return B04roleMenuTreeselect(roleId).then((response) => {
        this.menuOptions = response.menus;
        return response;
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getMenuTreeselect();
      this.open = true;
      this.title = "添加角色";
    },
    // 表单重置
    reset() {
      if (this.$refs.menu != undefined) {
        this.$refs.menu.setCheckedKeys([]);
      }
      (this.menuExpand = false),
        (this.menuNodeAll = false),
        (this.deptExpand = true),
        (this.deptNodeAll = false),
        (this.form = {
          roleId: undefined,
          roleName: undefined,
          roleKey: undefined,
          roleSort: 0,
          status: "0",
          menuIds: [],
          deptIds: [],
          menuCheckStrictly: true,
          deptCheckStrictly: true,
          remark: undefined,
        });
      this.resetForm("form");
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const roleId = row.roleId || this.ids;
      // this.B04roleMenuTreeselectData(roleId)
      const roleMenu = this.getRoleMenuTreeselect(roleId);
      getRole(roleId).then((response) => {
        console.log(response);
        this.form = response.data;
        this.open = true;
        this.$nextTick(() => {
          roleMenu.then((res) => {
            console.log(res);
            this.$refs.menu.setCheckedKeys(res.checkedKeys);
          });
        });
        this.title = "修改角色";
      });
    },

    /** 查询菜单树结构 */
    getMenuTreeselect() {
      menuTreeselect().then((response) => {
        console.log(response);
        this.menuOptions = response.data;
      });
    },
    //切换状态按钮
    handleSwitch(index, row) {
      console.log(row);
      const param = {
        roleId: row.roleId,
        status: row.status,
      };
      changeRoleStatus(param) //更新状态的接口
        .then((res) => {
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
      console.log(".11111是否被调用");
      this.listLoading = true;
      roleManageList(this.listQuery)
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
    //删除
    deleteClick(row) {
      const roleIds = row.roleId || this.ids;
      this.$confirm(
        '是否确认删除?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return delUser(roleIds);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
  },
};
</script>

<style scoped>
</style>
