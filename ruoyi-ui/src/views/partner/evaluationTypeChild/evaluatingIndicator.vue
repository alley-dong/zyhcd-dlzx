<template>
  <div class="app-container">
    <div style="display: flex">
      <div style="line-height: 36px">评价指标类型：{{ name }}</div>
      <div style="flex: 1; margin: 29px 0"></div>
      <buttonGroup
        oneButton="返回"
        twoButtonState
        threeButton="添加评分指标"
        leadingIn
        @handleFilter="backClick"
        @handleImportant="addClick()"
      />
    </div>

    <el-table :data="tableData" v-loading="loading" style="width: 100%">
      <el-table-column label="评价部分">
        <template slot-scope="scope">
          <div>
            {{ scope.row.type | data }}
          </div>
        </template>
      </el-table-column>
      <el-table-column label="维度" prop="dimension"> </el-table-column>
      <el-table-column label="评价内容" prop="content"> </el-table-column>
      <el-table-column label="评价方法" prop="method"> </el-table-column>
      <el-table-column align="right">
        <template slot-scope="scope">
          <el-button
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfserverperson:type:edit']"
            >修改</el-button
          >
          <el-button @click="handleDelete(scope.row)">删除</el-button>
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

    <el-dialog :title="title" :visible.sync="open">
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="100px"
      >
        <el-form-item label="评价部分" prop="type">
          <el-select
            v-model="formData.type"
            placeholder="请选择评价部分"
            clearable
            :style="{ width: '100%' }"
          >
            <el-option
              v-for="(item, index) in typeOptions"
              :key="index"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="维度" prop="dimension">
          <el-input
            v-model="formData.dimension"
            placeholder="请输入维度"
            clearable
            :style="{ width: '100%' }"
          >
          </el-input>
        </el-form-item>
        <el-form-item label="评价内容" prop="content">
          <el-input
            v-model="formData.content"
            type="textarea"
            placeholder="请输入评价内容"
            :autosize="{ minRows: 4, maxRows: 4 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <el-form-item label="评价方法" prop="method">
          <el-input
            v-model="formData.method"
            type="textarea"
            placeholder="请输入评价方法"
            :autosize="{ minRows: 4, maxRows: 4 }"
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer">
        <!-- <el-button @click="close">取消</el-button> -->
        <el-button type="primary" @click="handelConfirm">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import buttonGroup from "@/views/components/com/buttonGroup";
import {
  ContractMetricsList,
  F11ContractMetricsDelete,
  F11ContractMetricsAdd,
  F11ContractMetricsEditSave,
  F11ContractMetricsInfo,
} from "@/api/partner";
export default {
  data() {
    return {
      tableData: [],
      // 遮罩层
      loading: true,
      name: null,
      title: "",
      open: false,
      formData: {
        type: undefined,
        dimension: undefined,
        content: undefined,
        method: undefined,
      },
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      total: 0,
      rules: {
        type: [],
        dimension: [],
        content: [],
        method: [],
      },
      typeOptions: [
        {
          label: "技术部分",
          value: 1,
        },
        {
          label: "商务部分",
          value: 2,
        },
      ],
    };
  },
  filters: {
    data: function (value) {
      switch (value) {
        case 1:
          return "技术部分";
          break;
        case 2:
          return "商务部分";
          break;
        default:
          break;
      }
    },
  },
  mounted() {
    this.getList();
    console.log(this.$route);
    this.name = this.$route.query.name;
  },
  methods: {
    getList() {
      this.loading = true;
      let obj = {
        typeId: this.$route.params.id,
        pageNum: this.queryParams.pageNum,
        pageSize: this.queryParams.pageSize,
      };
      ContractMetricsList(obj).then((response) => {
        console.log(response);
        this.tableData = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    backClick() {
      this.$router.go(-1);
    },
 
    addClick() {
      console.log("添加");
      this.open = true;
      this.title = "添加";
    },
    handleUpdate(row) {
      console.log("修改");
      this.open = true;
      this.title = "修改";
      F11ContractMetricsInfo(row.id).then((res) => (this.formData = res.data));
    },
    handleDelete(row) {
      F11ContractMetricsDelete(row.id).then((res) => this.getList());
    },
    reset() {
      this.formData = {
        id: undefined,
        type: undefined,
        dimension: undefined,
        content: undefined,
        method: undefined,
      };
    },
    handelConfirm() {
      this.formData.typeId = this.$route.params.id;
      if (this.formData.id) {
        F11ContractMetricsEditSave(this.formData).then((res) => {
          this.getList();
          this.reset();
        });
      } else {
        F11ContractMetricsAdd(this.formData).then((res) => {
          this.getList();
          this.reset();
        });
      }
      this.open = false;
    },
  },
  components: {
    buttonGroup,
  },
};
</script>

<style lang="scss" scoped>
</style>
