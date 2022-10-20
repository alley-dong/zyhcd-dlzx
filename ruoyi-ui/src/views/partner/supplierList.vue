<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="供应商名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入供应商名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="" style="float:right">
        <buttonGroup 
          :addHasPermi="['pfserverperson:carrier:add']"
        @handleFilter="handleQuery" @handleAdd="addClick()" />
      </el-form-item>
    </el-form>

    <el-table 
    border
    v-loading="loading" :data="contractList">
      <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column>
      <el-table-column label="供应商名称" align="center" prop="name" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button class="button" @click="handleUpdate(scope.row)"
            size="small"
          type="primary"  plain
            v-hasPermi="['pfserverperson:carrier:edit']"
            >编辑</el-button
          >

          <el-button class="button" @click="handleDelete(scope.row)"
            size="small"

          type="danger"
            v-hasPermi="['pfserverperson:carrier:delete']"
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

    <el-dialog :visible.sync="open" :title="title">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="承运商名称" prop="name">
          <el-input
            v-model="formData.name"
            placeholder="请输入承运商名称"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button type="primary" :loading="btn_loading" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import buttonGroup from "@/views/components/com/buttonGroup";
import {
  CarriersList,
  CarriersAdd,
  CarriersInfo,
  CarriersEditSave,
  CarriersDelete
} from "@/api/partner";
export default {
  data() {
    return {
      btn_loading: false,
      open: false,
      // 标题
      title: "",
      // 遮罩层
      loading: true,
      // 合作方管理-合同表格数据
      contractList: [],
      // 总条数
      total: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
      },
      formData: {
        id: undefined,
        name: undefined,
      },
      rules: {
        name: [],
      },
    };
  },
  mounted() {
    this.getList();
  },
  methods: {
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 查询合作方管理-合同列表 */
    getList() {
      this.loading = true;
      CarriersList(this.queryParams).then((response) => {
        console.log(response);
        this.contractList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 新增
    addClick() {
      this.open = true;
      this.title = "新增";
          this.reset();
    },
    reset() {
      this.formData = {
        id: undefined,
        name: undefined,
      };
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.open = true;
      this.title = "修改";
      CarriersInfo(row.id).then((res) => {
        this.formData = res.data;
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$confirm(
        '是否确认删除供应商名称为"' + row.name + '"的数据项?',
        "警告",
        {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        }
      )
        .then(function () {
          return CarriersDelete(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    // 弹窗保存
    handelConfirm() {
      console.log(this.formData);
         this.btn_loading = true;
      setTimeout(() => {
        this.btn_loading = false;
      }, 1000);
      if (this.formData.id) {
        CarriersEditSave(this.formData).then((res) => {
          this.open = false;
          this.getList();
          this.reset();
        });
      } else {
        CarriersAdd(this.formData).then((res) => {
          this.open = false;
          this.getList();
          this.reset();
        });
      }
    },
  },
  components: {
    buttonGroup,
  },
};
</script>

<style lang="scss" scoped>
</style>