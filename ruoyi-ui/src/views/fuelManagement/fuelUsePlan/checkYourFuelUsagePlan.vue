<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="addForm"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <el-form-item label="车牌号" prop="licensePlate">
        <el-input
          v-model="queryParams.licensePlate"
          clearable
          size="small"
        />
      </el-form-item>
      <el-form-item style="float:right">
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleQuery"
        >查询</el-button
        >
        <el-button
          type="cyan"
          icon="el-icon-search"
          size="small "
          @click="handleback"
          >返回</el-button
        >
      </el-form-item>
    </el-form>
    <el-form label-width="100px">
      <el-form-item
        style="display:inline-block"
        label="油料金额合计(元):"
        label-width="130px"
      >
        {{ fuelPriceTotal }}
      </el-form-item>
      <el-form-item
        style="display:inline-block;padding-left: 10px"
        label="空放金额合计(元):"
        label-width="130px"
      >
        {{ ballastQuotaPriceTotal }}
      </el-form-item>
      <el-form-item
        style="display:inline-block;padding-left: 10px"
        label="倒货金额合计(元):"
        label-width="130px"
      >
        {{ pourQuotaPriceTotal }}
      </el-form-item>
    </el-form>
    <el-table v-loading="loading" :data="cardList" border :span-method="objectSpanMethod">
      <el-table-column label="计划日期" align="center" prop="planDate" />
      <el-table-column label="车牌号" align="center" prop="licensePlate" />
      <el-table-column label="车辆类型" align="center" prop="vehicleModelName" />
      <el-table-column label="线路" align="center" prop="routeName" />
      <el-table-column label="计划趟数" align="center" prop="planQuantity" />
      <el-table-column label="油料类型" align="center" prop="fuelTypeEnum" />
      <el-table-column label="油料单价（元）" align="center" prop="unitPrice" />
      <el-table-column label="油料定额（升）/趟" align="center" prop="fuelQuota" />
      <el-table-column label="油料金额（元）" align="center" prop="fuelPrice" />
      <el-table-column label="空放定额（升）/趟" align="center" prop="ballastQuota"></el-table-column>
      <el-table-column label="空放金额（元）" align="center" prop="ballastQuotaPrice" />
      <el-table-column label="倒货定额（升）/趟" align="center" prop="pourQuota"></el-table-column>
      <el-table-column label="倒货金额（元）" align="center" prop="pourQuotaPrice" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding "
        width="180px"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            type="primary"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['pfservercar:plan:edit']"
            style="color:#fff"
            >编辑</el-button
          >
          <el-button
            size="small "
            type="danger"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['pfservercar:plan:delete']"
            style="color:#fff"
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
  </div>
</template>

<script>
import {
  I08CarFuelOilPlanList,
  I08CarFuelOilPlanByLicensePlateDelete
} from "@/api/card/checkYourFuelUsagePlan";
export default {
  data() {
    return {
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
      // 油卡定额管理表格数据
      cardList: [],
      //
      fuelPriceTotal: 0,
      ballastQuotaPriceTotal: 0,
      pourQuotaPriceTotal: 0,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        planDate: ""
      },
      // 新增定额参数
      addform: {
        routeId: "",
        routeName: "",
        vehicleModel: "",
        vehicleModelName: "",
        quota: "",
        name: ""
      },
      // 表单参数
      form: {
        licensePlate: undefined,
        etcCardNumber: undefined,
        orderNumber: undefined,
        status: null,
        orderTime: undefined,
        method: undefined,
        obu: undefined,
        id: undefined
      },

      spanData:[],

      vehicleOptions: [],
      // 线路下拉
      routerOptions: [],

      // 表单校验
      rules: {},

      vehicleModelNamelist: [],
      oilslist: []
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 燃料使用计划列表 */
    getList() {
      this.loading = true;
      this.queryParams.planDate = this.$route.params.id;

      /*let testData = [
        {
          planDate:'2022-02',
          licensePlate:'吉AP1367',
          vehicleModelName:'重型普通货车',
          routeName:"青海大连线路",
          planQuantity:6,
          info:[
            {
              fuelTypeEnum: '-35',
              fuelQuota:1,
              unitPrice:2,
              fuelPrice:3,

              ballastQuota:1,
              ballastQuotaPrice:1,
              pourQuota:1,
              pourQuotaPrice:1,
            },
            {
              fuelTypeEnum: '-40',
              fuelQuota:4,
              unitPrice:5,
              fuelPrice:6,
              ballastQuota:1,
              ballastQuotaPrice:1,
              pourQuota:1,
              pourQuotaPrice:1,
            },
            {
              fuelTypeEnum: '0',
              fuelQuota:7,
              unitPrice:8,
              fuelPrice:9,
              ballastQuota:1,
              ballastQuotaPrice:1,
              pourQuota:1,
              pourQuotaPrice:1,
            },
          ],
        },
        {
          planDate:'2022-01',
          licensePlate:'吉AP1368',
          vehicleModelName:'重型普通货车',
          routeName:"青海大连线路",
          planQuantity:6,
          info:[
            {
              fuelTypeEnum: '0',
              fuelQuota:1,
              unitPrice:2,
              fuelPrice:3,
              ballastQuota:1,
              ballastQuotaPrice:1,
              pourQuota:1,
              pourQuotaPrice:1,
            },
            {
              fuelTypeEnum: '5',
              fuelQuota:4,
              unitPrice:5,
              fuelPrice:6,
              ballastQuota:1,
              ballastQuotaPrice:1,
              pourQuota:1,
              pourQuotaPrice:1,
            },
            {
              fuelTypeEnum: '-35',
              fuelQuota:7,
              unitPrice:8,
              fuelPrice:9,
              ballastQuota:1,
              ballastQuotaPrice:1,
              pourQuota:1,
              pourQuotaPrice:1,
            },
            {
              fuelTypeEnum: '-40',
              fuelQuota:10,
              unitPrice:11,
              fuelPrice:12,
              ballastQuota:1,
              ballastQuotaPrice:1,
              pourQuota:1,
              pourQuotaPrice:1,
            },
          ],
        }
      ];*/
      I08CarFuelOilPlanList(this.queryParams).then(response => {
        let testData = response.rows;
        this.total = response.total;
        this.loading = false;

        console.log("testData",testData)
        let showData = [];
        testData.forEach(r=>{
          if(r.info.length > 0){
            r.info.forEach(rc => {
              let d = {
                planDate:r.planDate,
                licensePlate:r.licensePlate,
                vehicleModel:r.vehicleModel,
                routeId:r.routeId,
                vehicleModelName:r.vehicleModelName,
                vehicleId:r.vehicleId,
                routeName:r.routeName,
                planQuantity:r.planQuantity,
                fuelTypeEnum:rc.fuelTypeEnum,
                unitPrice:rc.unitPrice,
                fuelQuota:rc.fuelQuota,
                fuelPrice:rc.fuelPrice,
                ballastQuota:rc.ballastQuota,
                ballastQuotaPrice:rc.ballastQuotaPrice,
                pourQuota:rc.pourQuota,
                pourQuotaPrice:rc.pourQuotaPrice,
              }
              showData.push(d);
            })
          } else {
            let d = {
              planDate:r.planDate,
              licensePlate:r.licensePlate,
              vehicleModel:r.vehicleModel,
              vehicleId:r.vehicleId,
              vehicleModelName:r.vehicleModelName,
              routeId:r.routeId,
              routeName:r.routeName,
              planQuantity:r.planQuantity,
            }
            showData.push(d);
          }
        })
        console.log("showData",showData)
        this.cardList = showData;

        if(response.rows.length >0){
          response
          this.fuelPriceTotal = response.fuelPriceTotal;
          this.ballastQuotaPriceTotal = response.ballastQuotaPriceTotal;
          this.pourQuotaPriceTotal = response.pourQuotaPriceTotal;
        } else {
          this.fuelPriceTotal = 0;
          this.ballastQuotaPriceTotal = 0;
          this.pourQuotaPriceTotal = 0;
        }


        this.getSpanData(showData);

      });



    },



    getSpanData(data) {
      // 存放计算好的合并单元格的规则
      this.spanData = []
      for (var i = 0; i < data.length; i++) {
        if (i === 0) {
          this.spanData.push(1)
          this.pos = 0
        } else {
          // 判断当前元素与上一个元素是否相同
          if (data[i].licensePlate === data[i - 1].licensePlate) {

            this.spanData[this.pos] += 1
            this.spanData.push(0)

          } else {
            this.spanData.push(1)
            this.pos = i
          }
        }
      }
    },

    objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      // 需要合并的列
      if (columnIndex === 0 || columnIndex === 1 || columnIndex === 2 || columnIndex === 3 || columnIndex === 4 || columnIndex === 13 ) {
        const _row = this.spanData[rowIndex]
        const _col = _row > 0 ? 1 : 0
        return {
          rowspan: _row,
          colspan: _col
        }
      }
    },

    // 表格合并
    /*objectSpanMethod({ row, column, rowIndex, columnIndex }) {
      let { spanrow } = row;
      if (spanrow) {
        let spanItem = spanrow[columnIndex];
        if (spanItem) {
          let { linespan, rowspan,  rowNum} = spanItem;
          if(columnIndex === rowspan) {
            if (rowIndex === rowNum) {
              let arr = [linespan, 1];
              return arr;
            } else {
              return [0, 0];
            }
          }
          
        }
      }
    },*/
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 返回
    handleback() {
      // console.log(this.$router.back())
      this.$router.back();
    },

    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加油卡定额管理";
    },
    /** 编辑按钮 */
    handleUpdate(row) {
      console.log("传参前车辆id",row),
      this.$router.push({
        path: "/editFuelUsagePlan",
        query: {
          id: row.id,
          planDate:row.planDate,
          licensePlate: row.licensePlate,
          vehicleModel: row.vehicleModel,
          vehicleModelName: row.vehicleModelName,
          vehicleId:row.vehicleId,
          routeId:row.routeId
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id;
      const planDate = row.planDate;
      const licensePlate = row.licensePlate;
      // TODO  此处需要处理删除功能
      this.$confirm('是否确认删除"' + row.licensePlate + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(function() {
          return I08CarFuelOilPlanByLicensePlateDelete({licensePlate:licensePlate,planDate:planDate});
        })
        .then(() => {
          this.getList();
          this.msgSuccess("删除成功");
        });
    }
  }
};
</script>
