<template>
  <div class="app-container">
    <div style="overflow: auto">
      <div style="float: right">
        <el-button type="primary" @click="backClick">返回</el-button>
        <!-- <el-button type="primary" @click="editClick">提交</el-button> -->
        <!--size="small"  -->
      </div>
    </div>

    <el-tabs v-model="activeName" type="card" @tab-click="handleClick">
      <el-tab-pane label="发车点检" name="start">
        <el-row :gutter="15">
          <el-form
            ref="elForm"
            v-if="startData"
            :model="startData"
            size="medium"
            label-width="100px"
          >
            <el-col :span="12">
              <el-form-item label="上报人" prop="driverName">
                <div>{{ startData.driverName }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="上报时间" prop="createTime">
                <div>{{ startData.createTime }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="运单号" prop="shipmentXid">
                <div>{{ startData.shipmentXid }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="任务线路" prop="routeName">
                <div>{{ startData.routeName }}</div>
              </el-form-item>
            </el-col>
          </el-form>
        </el-row>
        <el-table
          v-if="startData && startData.tTransportTaskInspectItemVms"
          :data="startData.tTransportTaskInspectItemVms"
        >
          <el-table-column
            label="序号"
            align="center"
            type="index"
            width="50"
          />
          <el-table-column label="点检内容" align="center" prop="typeEnum" />
          <el-table-column label="点检结果" align="center" prop="statusEnum" />
          <el-table-column label="点检图片" align="center" prop="files">
            <template slot-scope="scope">
              <div v-if="scope.row.fileList">
                <div
                  v-for="(item, index) in scope.row.fileList"
                  :key="index"
                  style="
                    width: 100px;
                    height: 100px;
                    display: inline-block;
                    margin-right: 20px;
                  "
                >
                  <img :src="item" alt="" style="width: 100%; height: 100%" />
                </div>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="在途点检" name="intransit">
        <div
          v-for="item in intransitDataList"
          :key="item.id"
          style="margin-bottom: 80px"
        >
          <el-row :gutter="15">
            <el-form
              ref="elForm"
              :model="item"
              size="medium"
              label-width="100px"
            >
              <el-col :span="12">
                <el-form-item label="上报人" prop="driverName">
                  <div>{{ item.driverName }}</div>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="上报时间" prop="createTime">
                  <div>{{ item.createTime }}</div>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="运单号" prop="shipmentXid">
                  <div>{{ item.shipmentXid }}</div>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="任务线路" prop="routeName">
                  <div>{{ item.routeName }}</div>
                </el-form-item>
              </el-col>
            </el-form>
          </el-row>
          <el-table
            v-if="item.tTransportTaskInspectItemVms"
            :data="item.tTransportTaskInspectItemVms"
          >
            <el-table-column
              label="序号"
              align="center"
              type="index"
              width="50"
            />
            <el-table-column label="点检内容" align="center" prop="typeEnum" />
            <el-table-column
              label="点检结果"
              align="center"
              prop="statusEnum"
            />
            <el-table-column label="点检图片" align="center" prop="files">
              <template slot-scope="scope">
                <div v-if="scope.row.fileList">
                  <div
                    v-for="(item, index) in scope.row.fileList"
                    :key="index"
                    style="
                      width: 100px;
                      height: 100px;
                      display: inline-block;
                      margin-right: 20px;
                    "
                  >
                    <img :src="item" alt="" style="width: 100%; height: 100%" />
                  </div>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-tab-pane>
      <el-tab-pane label="归队点检" name="decycle">
        <el-row :gutter="15">
          <el-form
            v-if="decycleData"
            ref="elForm"
            :model="decycleData"
            size="medium"
            label-width="100px"
          >
            <el-col :span="12">
              <el-form-item label="上报人" prop="driverName">
                <div>{{ decycleData.driverName }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="上报时间" prop="createTime">
                <div>{{ decycleData.createTime }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="运单号" prop="shipmentXid">
                <div>{{ decycleData.shipmentXid }}</div>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="任务线路" prop="routeName">
                <div>{{ decycleData.routeName }}</div>
              </el-form-item>
            </el-col>
          </el-form>
        </el-row>
        <el-table
          v-if="decycleData && decycleData.tTransportTaskInspectItemVms"
          :data="decycleData.tTransportTaskInspectItemVms"
        >
          <el-table-column
            label="序号"
            align="center"
            type="index"
            width="50"
          />
          <el-table-column label="点检内容" align="center" prop="typeEnum" />
          <el-table-column label="点检结果" align="center" prop="statusEnum" />
          <el-table-column label="点检图片" align="center" prop="files">
            <template slot-scope="scope">
              <div v-if="scope.row.fileList">
                <div
                  v-for="(item, index) in scope.row.fileList"
                  :key="index"
                  style="
                    width: 100px;
                    height: 100px;
                    display: inline-block;
                    margin-right: 20px;
                  "
                >
                  <img :src="item" alt="" style="width: 100%; height: 100%" />
                </div>
              </div>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import {
  transportTaskInspectP10TransportTaskInspectInfo,
  tRouteP01RouteDelete,
} from "@/api/taskManagement/index";
export default {
  data() {
    return {
      //   formData: {
      //     driverName: undefined,
      //     createTime: undefined,
      //     shipmentXid: undefined,
      //     routeName: undefined,
      //   },
      //   dataList:[]
      activeName: "start",
      startData: undefined,
      intransitDataList: [],
      decycleData: undefined,
    };
  },
  mounted() {
    transportTaskInspectP10TransportTaskInspectInfo({
      id: this.$route.params.id,
    }).then((res) => {
      for (const item of res.data.tTransportTaskInspectVms) {
        console.log(item.type);
        switch (item.type) {
          case 1:
            this.startData = item;
            // startList = item.tTransportTaskInspectItemVms
            break;
          case 2:
            this.intransitDataList.push(item);
            break;
          case 3:
            this.decycleData = item;
            // decycleList = item.tTransportTaskInspectItemVms
            break;
          default:
            break;
        }
      }

      console.log("1发车点检", this.startData);
      console.log("2在途点检", this.intransitDataList);
      console.log("3归队点检", this.decycleData);
    });
  },
  methods: {
    backClick() {
      this.$router.go(-1);
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
};
</script>

<style lang="scss" scoped>
.border {
  display: flex;
  width: 100%;
  height: 40px;
  margin-bottom: 20px;
  .text {
    width: 80px;
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