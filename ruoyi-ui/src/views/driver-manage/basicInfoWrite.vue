<template>
  <div>
    <!-- 笔试记录 -->
    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="submitForm" :loading="saveLoading"
          >保存</el-button
        >
        <el-button @click="backClick">返回</el-button>
      </div>
    </div>
        <div style="border: 1px solid #ccc; padding: 0 0 50px 10px">
          <h3>单选题（ 每题1分 共40分 ）</h3>
          <ol>
            <!-- TODO:笔试没做完 -->
            <li v-for="item in radio" :key="item.id">
              <p>
                {{ item.name }}
                <span style="float: right">（正确答案：{{item.trueAnswer }} 已选答案：{{item.answer}}）</span>
              </p>
              <div>
                <div style="display: inline-block; margin-right: 20px">
                  A.{{ item.optionA }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  B.{{ item.optionB }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  C.{{ item.optionC }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  D.{{ item.optionD }}
                </div>
              </div>
            </li>
          </ol>
          <h3>多选题（ 每题2分 共30分 ）</h3>
          <ol>
            <li v-for="item in checkBox" :key="item.id">
              <p>
                {{ item.name }}

                <span style="float: right"
                  >（正确答案：{{item.trueAnswer}} 已选答案：{{item.answer}}）</span
                >
              </p>
              <div>
                <div style="display: inline-block; margin-right: 20px">
                  A.{{ item.optionA }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  B.{{ item.optionB }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  C.{{ item.optionC }}
                </div>
                <div style="display: inline-block; margin-right: 20px">
                  D.{{ item.optionD }}
                </div>
              </div>
            </li>
          </ol>
          <h3>判断题（ 每题2分 共30分 ）</h3>
          <ol>
            <li v-for="item in judgment" :key="item.id">
              <p>
                {{ item.name }}
                <span style="float: right">（正确答案：{{item.trueAnswer == 'B'?'错':'对'}} 已选答案：{{item.answer?(item.answer=='B'?'错':'对'):''}} ）</span>
              </p>
            </li>
          </ol>
          <!-- 分数统计 -->
          <!-- <table border="1" class="Score-statistics">
            <tr>
              <th>单选题（40分）</th>
              <th>多选题（30分）</th>
              <th>判断题（30分）</th>
              <th>总计</th>
            </tr>
            <tr>
              <td>30</td>
              <td>30</td>
              <td>30</td>
              <td>30</td>
            </tr>
          </table> -->
          <el-table :data="tableData" border style="width: 60%; margin: auto">
            <el-table-column prop="radio" label="单选题（40分）" width="180">
            </el-table-column>
            <el-table-column prop="choice" label="多选题（30分）" width="180">
            </el-table-column>
            <el-table-column prop="judge" label="判断题（30分）">
            </el-table-column>
            <el-table-column prop="total" label="总计"> </el-table-column>
          </el-table>
          <div class="wrap">
            <div class="text" style="font-weight: 600">笔试结果</div>
            <div class="line"></div>
          </div>
          <!-- <div style="margin-left: 50px">
            <span style="margin-right: 40px">{{
              formData.examStatus | formDataCheck
            }}</span>
            <span>{{ formData.examRemarks }}</span>
          </div> -->
              <el-form
      ref="elForm"
      :model="formData"
      :rules="rules"
      size="medium"
      label-width="100px"
    >
      <el-form-item label="" prop="examStatus">
        <el-radio-group v-model="formData.examStatus" size="medium">
          <el-radio
            v-for="(item, index) in field102Options"
            :key="index"
            :label="item.value"
            :disabled="item.disabled"
            >{{ item.label }}</el-radio
          >
        </el-radio-group>
      </el-form-item>
      <el-form-item label="" prop="examRemarks">
        <el-input
          v-model="formData.examRemarks"
          type="textarea"
          placeholder="请输入"
          :autosize="{ minRows: 4, maxRows: 4 }"
          :style="{ width: '100%' }"
        ></el-input>
      </el-form-item>
    </el-form>
        </div>
  </div>
</template>

<script>
import {
  driverWrittenExamination,
  A16WrittenReviewSave,
  A11Info,
} from "@/api/driver/interviewManage";
export default {
  data() {
    return {
       field102Options: [
        {
          label: "通过",
          value: 3,
        },
        {
          label: "不通过",
          value: 2,
        },
        // {
        //   label: "储备",
        //   value: 4,
        // },
      ],
      tableData: [
        // {
        //   radio: "30",
        //   choice: "30",
        //   judge: "30",
        //   total: "30",
        // },
      ],
      checkBox: [],
      judgment: [],
      radio: [],
      saveLoading: false,
      formData: {
        id:undefined,
        examStatus: undefined,
        examRemarks: undefined,
      },
      field102Options: [
        {
          label: "通过",
          value: 3,
        },
        {
          label: "不通过",
          value: 2,
        },
      //  {
      //     label: "储备",
      //     value: 4,
      //   },
      ],
    };
  },
  mounted() {
    this.init();
        this.formData.id = this.$route.params.id

  },
  filters: {
       formDataCheck(val) {
      switch (val) {
        case 1:
          return "进行中";
          break;
        case 2:
          return "未通过";
          break;
        case 3:
          return "已通过";
          break;
        case 4:
          return "储备";
          break;
        default:
          break;
      }
    },
    trueAnswer(val){
      console.log(val);
      switch (val) {
        case 'A':
          return "对";
          break;
        case 'B':
          return "错";
          break;
     
        default:
          break;
      }
    }
  },
  methods: {
    init() {
      this.A11InfoData(); // 基本信息
      this.driverWrittenExaminationData(); //笔试详情
    },
    // // 基本信息
    A11InfoData() {
      A11Info(this.$route.params.id).then((res) => {
        console.log(res.data.interviewRecord);
        // TODO:

        this.formData = res.data;
      });
    },
    // 笔试详情
    driverWrittenExaminationData() {
      driverWrittenExamination(this.$route.params.id).then((res) => {
        console.log(res);
        this.checkBox = res.data.checkBox;
        this.judgment = res.data.judgment;
        this.radio = res.data.radio;
        let obj = {
          radio: res.data.checkScore,
          choice: res.data.judgmentScore,
          judge: res.data.radioScore,
          // judge: res.data.judgmentScore,
          total: res.data.radioScore + res.data.checkScore + res.data.judgmentScore,
        };
         if (!obj.radio) {
        this.tableData = [];
          
        }else{
        this.tableData = [obj];
        }
        // this.tableData = [obj];
      });
    },
    // 返回
    backClick() {
      this.$router.go(-1)
      // this.$router.push({ path: "/interviewManage", name: "interviewManage" });
    },
    // 保存
    submitForm() {
      this.saveLoading = true;
      A16WrittenReviewSave(this.formData).then((res) => {
        this.saveLoading = false;
        // this.$router.push({
        //   path: `/basicInfo/${this.$route.params.id}`,
        //   query: { num: "2" },
        // });
        this.backClick()
      });
    },
  },
};
</script>

<style lang="scss" scoped>
.wrap {
  width: 100%;
  height: 40px;
  position: relative;
}
.text {
  width: 10%;
  line-height: 40px;
  text-align: center;
  font-size: 16px;
  color: black;
  float: left;
  font-weight: 400;
}
.line {
  width: 90%;
  border-bottom: 3px solid #333399;
  position: absolute;
  left: 10%;
  top: 50%;
  z-index: 1;
  float: left;
}
.box {
  margin: 30px 50px 10px 0;
  text-align: right;
  .btn-group {
    display: inline-block;
  }
}
.Score-statistics {
  margin: auto;
}
.Score-statistics th {
  width: 170px;
  text-align: center;
}
.Score-statistics td {
  text-align: center;
}
</style>
