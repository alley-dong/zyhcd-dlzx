<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick">返回</el-button>
        <!-- <el-button type="primary" @click="saveClick">保存</el-button> -->
        <el-button type="primary" @click="editClick">提交</el-button>
        <!--size="small"  -->
      </div>
    </div>
    <div class="border">
      <div class="text">新增</div>
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

      <!--<el-table-column label="盈亏" align="center" prop="status">
        <template slot-scope="scope">
          {{ scope.row.status | status }}
        </template>
      </el-table-column>
      <el-table-column label="差异数量" align="center" prop="difference" />-->

      <el-table-column
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
      </el-table-column>
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
// import {
//   checkItemK15CheckList,
//   // checkItemK15EditSave,
//   checkItemK15Add,
// } from "@/api/stock/child/index";
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
    console.log(this.$route.params);
    this.goodsTypeName = this.$route.query.goodsTypeName;
    this.id = this.$route.params.id;
    this.checkId = this.$route.query.checkId;
    this.getList();
  },
  methods: {
    getList() {
      this.loading = true;
      checkItemK15CheckList({ checkId: this.id }).then((response) => {
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
        this.getList();
      });

      // this.getList();
    },
    backClick() {
      console.log("返回");
      this.$router.go(-1);
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
      let { checkId, id } = this;
      console.log(this.dataList);
      let gGoodsCheckItems = [];
      for (const item of this.dataList) {
        let obj = {};
        obj.status = item.status?item.status:3;
        obj.difference = item.difference?Math.abs(item.difference):0;
        obj.goodsId = item.goodsId;
        gGoodsCheckItems.push(obj);
      }
      checkItemK15Add({ gGoodsCheckItems, id }).then((res) => {
        // let form = {};
        // form.goodsTypeName = this.goodsTypeName;
        // form.checkId = this.checkId;
        // this.$router.push({
        //   path: "/seeInventoryManagement/" + this.id,
        //   query: {
        //     goodsTypeName: this.goodsTypeName,
        //     checkId: this.checkId,
        //   },
        // });

        this.backClick();
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
