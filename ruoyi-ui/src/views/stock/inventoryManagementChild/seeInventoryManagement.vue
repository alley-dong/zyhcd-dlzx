<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick">返回</el-button>
        <!-- <el-button type="primary" @click="saveClick">保存</el-button>
        <el-button type="primary" @click="editClick">提交</el-button> -->
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">查看</div>
      <div class="xian"></div>
    </div>
    <div style="margin-bottom: 20px">物品类型：{{ goodsTypeName }}</div>

    <el-table v-loading="loading" :data="dataList">
      <el-table-column label="物品编号" align="center" prop="goodsCode" />
      <el-table-column label="物品名称" align="center" prop="goodsName" />
      <el-table-column label="规格" align="center" prop="specification" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="库存" align="center" prop="currentQuantity" />
      <el-table-column label="盘点数量" align="center" prop="checkQuantity" />
      <!-- <el-table-column label="盈亏" align="center" prop="status" /> -->

      <el-table-column label="盈亏" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status | status }}
        </template>
      </el-table-column>
      <el-table-column label="差异数量" align="center" prop="difference" />

      <!-- <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="small"
            @click="handleUpdate(scope.row)"
            type="primary"
            plain
            >编辑</el-button
          >
        </template>
      </el-table-column> -->
    </el-table>

    <el-dialog title="编辑" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" label-width="100px">
        <el-form-item label="盘点数量" prop="checkQuantity">
          <el-input
            v-model="form.checkQuantity"
            placeholder="请输入盘点数量"
            clearable
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  checkItemK15CheckList,
  checkItemK15EditSave,
  checkItemK15Add,
  checkItemK15Preservation,
} from "@/api/stock/index";

// import { checkItemK15CheckList } from "@/api/stock/child/index";
export default {
  data() {
    return {
      open: false,
      // 遮罩层
      loading: true,
      goodsTypeName: undefined,
      dataList: [],
      form: {
        id: undefined,
        checkQuantity: undefined,
      },
    };
  },
  filters: {
    status: function (value) {
      switch (value) {
        //   盘盈 2盘亏 3 -
        case 1:
          return "盘盈";
          break;
        case 2:
          return "盘亏";
          break;
        case 3:
          return "-";
          break;
        default:
          break;
      }
    },
  },
  mounted() {
    console.log(this.$route.query);
    this.goodsTypeName = this.$route.query.goodsTypeName;
    this.id = this.$route.params.id;
    this.checkId = this.$route.query.id;
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      console.log(this.checkId);
      checkItemK15CheckList({ checkId: this.checkId }).then((response) => {
        // this.dataList = response.rows;
        // this.dataList = response.data.rows; //TODO:删了
        this.dataList = response.rows;//TODO:留
        // this.total = response.total;
        this.loading = false;
      });
    },
    handleUpdate(row) {
      this.form = row;
      this.open = true;
      console.log("编辑");
    },
    submitForm() {
      console.log("提交");
      let { id, checkQuantity } = this.form;
      //   for (const item of this.dataList) {
      //       console.log(item.id == id);
      //   }
      //   debugger
      checkItemK15EditSave({ id, checkQuantity }).then((res) => {
        this.open = false;
      });

      // this.getList();
    },
    backClick() {
      console.log("返回");
      this.$router.go(-1);
      // this.$router.push({path:'/inventoryManagement'});
    },
    saveClick() {
      console.log("保存");
      for (const item of this.dataList) {
        item.checkId = this.checkId;
        item.goodsId = item.id;
      }
      checkItemK15Preservation({ gGoodsCheckItemList: this.dataList }).then(
        (res) => {
          this.backClick();
        }
      );
    },
    editClick() {
      console.log("提交");
      //   debugger;
      checkItemK15Add({ checkId: this.checkId }).then((res) => {
        // this.backClick();
      });
    },
  },
};
</script>

<style lang="scss" scoped>
</style>

<style lang="scss" scoped>
.border {
  display: flex;
  width: 100%;
  height: 40px;
  margin-bottom: 20px;
  .text {
    width: 50px;
    text-align: right;
    line-height: 40px;
    margin-right: 30px;
  }
  .xian {
    flex: 1;
    border-bottom: 3px solid #333399;
    height: 50%;
  }
}
</style>