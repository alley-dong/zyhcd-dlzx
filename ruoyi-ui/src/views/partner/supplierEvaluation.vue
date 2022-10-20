<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      label-width="100px"
    >
      <el-form-item label="评价月份" prop="evaluateMonth">
        <!-- <el-input
          v-model=""
          placeholder="请输入供应商名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        /> -->
        <el-date-picker
          clearable
          v-model="queryParams.evaluateMonth"
          size="small"
          style="width: 200px"
          type="date"
          value-format="yyyy-MM-dd"
          @keyup.enter.native="handleQuery"
          placeholder="选择评价月份"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item label="供应商名称" prop="evaluateMonth">
        <el-input
          v-model="queryParams.carrierName"
          placeholder="请输入供应商名称"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item label="" style="float:right">
        <buttonGroup
          :addHasPermi="['pfserverperson:evaluate:add']"
          @handleFilter="handleQuery"
          @handleAdd="addClick()"
        />
      </el-form-item>
    </el-form>

    <el-table 
    border
    v-loading="loading" :data="contractList">
      <!-- <el-table-column
        label="序号"
        type="index"
        width="50"
        align="center"
      ></el-table-column> -->
      <el-table-column
        sortable
        label="评价月份"
        align="left"
        prop="evaluateMonth"
      />
      <el-table-column label="供应商名称" align="left" prop="carrierName" />
      <el-table-column label="评价指标类型" align="left" prop="typeName" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            class="button"
            @click="handleUpdate(scope.row)"
            size="small"
            v-hasPermi="['pfserverperson:evaluate:info']"
            type="primary"
            >查看评价</el-button
          >
          <el-button
            class="button"
            @click="handleDelete(scope.row)"
            size="small"
            v-hasPermi="['pfserverperson:evaluate:delete']"
            type="danger"
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
        <el-form-item label="评价月份" prop="evaluateMonth">
          <el-date-picker
            v-model="formData.evaluateMonth"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            :style="{ width: '100%' }"
            placeholder="请选择评价月份"
            clearable
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="供应商名称" prop="carrierId">
          <el-select
            v-model="formData.carrierId"
            placeholder="请选择供应商名称"
            clearable
            :style="{ width: '100%' }"
            @change="carrierChange"
          >
            <el-option
              v-for="(item, index) in carrierList"
              :key="index"
              :label="item.name"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="评价指标类型" prop="typeId">
          <el-select
            v-model="formData.typeId"
            placeholder="请选择评价指标类型"
            clearable
            @change="typeChange"
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in typeList"
              :key="index"
              :label="item.name"
              :value="item.id"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <el-button type="primary"  :loading="btn_loading" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import buttonGroup from "@/views/components/com/buttonGroup";
import {
  F12ContractEvaluateList,
  CarriersList,
  ContractMetricsTypeList,
  F12ContractEvaluateAdd,
  evaluateF12ContractEvaluateDelete,
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
      carrierList: [],
      typeList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        evaluateMonth: null,
        carrierName: null,
      },
      formData: {
        // id: undefined,
        evaluateMonth: undefined,
        typeName: undefined,
        carrierId: undefined,
        typeId: undefined,
        carrierName: undefined,
      },
      rules: {
        name: [],
      },
    };
  },
  mounted() {
    this.getList();
    CarriersList({}).then((response) => {
      console.log(response.rows);
      this.carrierList = response.rows;
    });
    ContractMetricsTypeList({}).then((response) => {
      console.log(response.rows);
      this.typeList = response.rows;
    });
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
      F12ContractEvaluateList(this.queryParams).then((response) => {
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
      console.log(row.typeName);
      this.$router.push({
        path: "/detailComments/" + row.id,
        query: { id: row.id, typeId: row.typeId, month: row.evaluateMonth },
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
          return evaluateF12ContractEvaluateDelete(ids);
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    },
    // 弹窗保存
    handelConfirm() {
      console.log(this.formData);
      // if (this.formData.id) {
      //   CarriersEditSave(this.formData).then((res) => {
      //     this.open = false;
      //     this.getList();
      //     this.reset();
      //   });
      // } else {
      // return
      this.btn_loading = true;
      setTimeout(() => {
        this.btn_loading = false;
      }, 1000);
      F12ContractEvaluateAdd(this.formData).then((res) => {
        this.open = false;
        this.getList();
        this.reset();
      });
      // }
    },
    // 供应商选择事件
    carrierChange(val) {
      console.log(val);
      for (const item of this.carrierList) {
        if (item.id == val) {
          console.log(item);
          this.formData.carrierName = item.name;
        }
      }
    },
    // 评价指标选择事件
    typeChange(val) {
      console.log(val);
      for (const item of this.typeList) {
        if (item.id == val) {
          this.formData.typeName = item.name;
          // console.log(item);
        }
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