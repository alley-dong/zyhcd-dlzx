<template>
  <div class="app-container">
    <!-- 数据统计 -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <!-- TODO:车牌号 -->
      <el-form-item label="车牌号" prop="vehicleId">
        <!-- <el-select
          v-model="queryParams.vehicleId"
          placeholder="请选择车牌号"
          clearable
          size="small"
        >
          <el-option
            v-for="item in option"
            :key="item.id"
            :label="item.licensePlate"
            :value="item.id"
          />
        </el-select> -->
        <el-autocomplete
          class="inline-input"
          v-model="queryParams.licensePlate"
          :fetch-suggestions="querySearch"
          placeholder="请选择车牌号"
          clearable
          @select="handleSelect"
          @change="paramChange"
          :style="{ width: '100%' }"
        >
          <template slot-scope="{ item }" :style="{ width: '100%' }">
            <!-- <div class="name">{{ item.id }}</div> -->
            <span class="addr">{{ item.licensePlate }}</span>
          </template>
        </el-autocomplete>
      </el-form-item>
      <buttonGroup
        @handleFilter="handleQuery"
        twoButtonState
        backButton
        @handleBack="$router.go(-1)"
        style="float: right"
      />
    </el-form>
    <div v-if="formData.month" style="margin-bottom: 30px">
      {{ formData.month }}
    </div>

    <el-table v-loading="loading" :data="repairList">
      <el-table-column label="序号" align="center" type="index" width="50">
      </el-table-column>
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column
        label="月末时点里程"
        align="center"
        prop="monthEndMileage"
      />
      <el-table-column
        label="月度行驶总里程（公里）"
        align="center"
        prop="lastMonthEndMileage"
      />
      <el-table-column
        label="月度加油量（升）"
        align="center"
        prop="monthFuelVolume"
      />
      <el-table-column
        label="月度尿素领用量（升）"
        align="center"
        prop="monthUreaVolume"
      />
      <el-table-column label="百公里油耗" align="center" prop="kmFuel" />
      <el-table-column label="百公里尿素消耗" align="center" prop="kmUrea" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
        width="200"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            @click="handleClick(scope.row)"
            plain
            >编辑</el-button
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


    <el-dialog
      :visible.sync="open"
      v-bind="$attrs"
      v-on="$listeners"
      title="修改"
      width="500px"
      append-to-body
    >
      <el-form
        ref="elForm"
        :model="formData"
        :rules="rules"
        size="medium"
        label-width="120px"
      >
        <el-form-item label="月末时点里程" prop="monthEndMileage">
          <el-input
            v-model="formData.monthEndMileage"
            placeholder="请输入月末时点里程"
            clearable
            :style="{ width: '100%' }"
          ></el-input>
        </el-form-item>
        <div
          style="display: flex; justify-content: center; align-items: center"
        >
          <el-button type="primary" style="width: 70px" @click="handelConfirm"
            >保存</el-button
          >
        </div>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import buttonGroup from "@/views/components/com/buttonGroup";
import {
  vehicleConsumeMonthN03List,
  vehicleConsumeMonthN03Edit,
  commonSelectBoxN02CarNumList,
} from "@/api/dataStatistics/index";
export default {
  components: {
    buttonGroup,
  },
  data() {
    return {
      // 弹窗打开
      open: false,
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
      // 维修保养-维修保养单表格数据
      repairList: [],
      //   下拉
      //   repairListOption: [],
      option: [], //
      // 弹出层标题
      title: "",
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        month: null,
        vehicleId: undefined,
        licensePlate: undefined,
      },
      formData: {
        monthEndMileage: undefined,
      },
      rules: {
        monthEndMileage: [],
      },
    };
  },
  created() {
    vehicleConsumeMonthN03List({month:this.$route.params.id}).then((response) => {
      console.log("哈哈哈",response)
      this.option = response.rows;
    });
  },
  mounted() {
    console.log(this.$route.params.id);
    this.formData.month = this.queryParams.month = this.$route.params.id;
    this.getList(true);
  },
  methods: {
    querySearch(queryString, cb) {
      var restaurants = this.option;
      var results = queryString
        ? restaurants.filter(this.createFilter1(queryString))
        : restaurants;
      cb(results);
    },
    handleSelect(item) {
      console.log(item);
      this.queryParams.licensePlate = item.licensePlate;
      this.queryParams.vehicleId = item.vehicleId;
    },

    paramChange(){
      if(this.queryParams.licensePlate){
        var flg = false;
        this.option.forEach(op=>{
          if(op.licensePlate == this.queryParams.licensePlate){
            this.queryParams.vehicleId = op.vehicleId;
            flg = true
          }
        })
        if(!flg){
          this.queryParams.licensePlate = ""
        }
      }
    },
    createFilter1(queryString) {
      return (restaurant) => {
        return (
          restaurant.licensePlate
            .toLowerCase()
            .indexOf(queryString.toLowerCase()) === 0
        );
      };
    },
    /** 查询列表 */
    getList(bool) {
      this.loading = true;
      vehicleConsumeMonthN03List(this.queryParams).then((response) => {
        if (bool) {
          this.repairListOption = response.rows;
        }
        for (const item of response.rows) {
          item.total = item.unitPrice * item.amount;
        }
        this.repairList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      if(!this.queryParams.licensePlate){
        this.queryParams.vehicleId = undefined
      }
      this.getList();
    },
    // 编辑
    handleClick(row) {
      this.formData.id = row.id;
      this.open = true;
      console.log("查看", row);
    },
    // 保存
    handelConfirm() {
      console.log("保存");
      vehicleConsumeMonthN03Edit(this.formData).then((res) => {
        this.open = false;
        this.getList()
      });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>
