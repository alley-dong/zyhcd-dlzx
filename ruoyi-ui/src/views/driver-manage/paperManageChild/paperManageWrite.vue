<template>
  <div>
    <!-- 笔试记录 -->

    <div class="box">
      <div class="btn-group">
        <el-button type="primary" @click="backClick">返回</el-button>
      </div>
    </div>
    <div style="border: 1px solid #ccc; padding: 0 0 50px 10px; margin: 20px">
      <div class="wrap">
        <div class="text" style="font-weight: 600">查看试卷</div>
        <div class="line"></div>
      </div>
      <div>
        <h3>单选题（ 每题1分 共40分 ）</h3>
        <ol>
          <li v-for="item in radioList" :key="item.id">
            <p>
              {{item.name}}
              <span style="float: right">（正确答案：{{ item.answer }} ）</span>
            </p>
            <div>
              <div
                v-if="item.optionA"
                style="display: inline-block; margin-right: 20px"
              >
                A.{{ item.optionA }}
              </div>
              <div
                v-if="item.optionB"
                style="display: inline-block; margin-right: 20px"
              >
                B.{{ item.optionB }}
              </div>
              <div
                v-if="item.optionC"
                style="display: inline-block; margin-right: 20px"
              >
                C.{{ item.optionC }}
              </div>
              <div
                v-if="item.optionD"
                style="display: inline-block; margin-right: 20px"
              >
                D.{{ item.optionD }}
              </div>
            </div>
          </li>
        </ol>
      </div>
      <div>
        <h3>多选题（ 每题2分 共30分 ）</h3>
        <ol>
          <li v-for="item in checkList" :key="item.id">
            <p>
              {{item.name}}
              <span style="float: right">（正确答案：{{item.answer}}）</span>
            </p>
            <div>
              <div
                v-if="item.optionA"
                style="display: inline-block; margin-right: 20px"
              >
                A.{{ item.optionA }}
              </div>
              <div
                v-if="item.optionB"
                style="display: inline-block; margin-right: 20px"
              >
                B.{{ item.optionB }}
              </div>
              <div
                v-if="item.optionC"
                style="display: inline-block; margin-right: 20px"
              >
                C.{{ item.optionC }}
              </div>
              <div
                v-if="item.optionD"
                style="display: inline-block; margin-right: 20px"
              >
                D.{{ item.optionD }}
              </div>
            </div>
          </li>
        </ol>
      </div>
      <div>
        <h3>判断题（ 每题2分 共30分 ）</h3>
        <ol>
          <li v-for="item in ofList" :key="item.id">
            <p>
              {{item.name}}
              <span style="float: right">（正确答案：{{item.answer=='B'?'错':'对'}}）</span>
              <!-- <span style="float: right">（正确答案： ）</span> -->
            </p>
          </li>
        </ol>
      </div>
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
        <el-table-column prop="judge" label="判断题（30分）"> </el-table-column>
        <el-table-column prop="total" label="总计"> </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { dExamPaperGetInfo } from "@/api/driver/noticeManage";

export default {
  data() {
    return {
      radioList: [],
      checkList: [],
      ofList: [],
      tableData: [
        {
          radio: "40",
          choice: "30",
          judge: "30",
          total: "100",
        },
      ],
      dataList: [],
    };
  },
  mounted() {
    //
    this.init();
  },
  methods: {
    init() {
      this.dExamPaperGetInfoData();
    },
    // 试卷详情
    dExamPaperGetInfoData() {
      dExamPaperGetInfo(this.$route.params.id).then((res) => {
        // this.dataList = [
        //   {
        //     searchValue: null,
        //     createBy: null,
        //     createTime: null,
        //     updateBy: null,
        //     updateTime: null,
        //     remark: null,
        //     beginTime: null,
        //     endTime: null,
        //     params: {},
        //     id: "0fb8936b-b94d-4b88-8264-fbdf1a11b535",
        //     paperId: "3043b5d6-fd2c-4c50-ba68-085afe43f4b2",
        //     problemId: "0618ede5-aca4-4930-8165-67627b403e36",
        //     name: "试题1",
        //     type: 2,
        //     optionA: "撒大声地",
        //     optionB: "收到阿萨达大萨达",
        //     optionC: "第三方士大夫",
        //     optionD: "试试",
        //     answer: "A,B",
        //     score: 2,
        //   },
        //   {
        //     searchValue: null,
        //     createBy: null,
        //     createTime: null,
        //     updateBy: null,
        //     updateTime: null,
        //     remark: null,
        //     beginTime: null,
        //     endTime: null,
        //     params: {},
        //     id: "16bb6978-9de6-446a-a0a3-107f47c55f80",
        //     paperId: "3043b5d6-fd2c-4c50-ba68-085afe43f4b2",
        //     problemId: "6ff4184a-aa56-49d9-b74e-b88fcf971ef3",
        //     name: "撒大声地",
        //     type: 1,
        //     optionA: "撒大声地",
        //     optionB: "收到阿萨达大萨达",
        //     optionC: "第三方士大夫",
        //     optionD: "试试",
        //     answer: "'d'",
        //     score: 1,
        //   },
        //   {
        //     searchValue: null,
        //     createBy: null,
        //     createTime: null,
        //     updateBy: null,
        //     updateTime: null,
        //     remark: null,
        //     beginTime: null,
        //     endTime: null,
        //     params: {},
        //     id: "ae725b25-a29c-41bb-8b3b-58074163e92c",
        //     paperId: "3043b5d6-fd2c-4c50-ba68-085afe43f4b2",
        //     problemId: "81d7f9b9-ac8e-4c31-a3a6-1cd09a374e95",
        //     name: "判断题",
        //     type: 3,
        //     optionA: "撒大声地",
        //     optionB: "收到阿萨达大萨达",
        //     optionC: "第三方士大夫",
        //     optionD: "试试",
        //     answer: "'b'",
        //     score: 2,
        //   },
        // ]; //TODO:
        // console.log(JSON.stringify(res));

        let radio = [];
        let check = [];
        let ofList = [];

        for (const item of res.data) {
          switch (item.type) {
            case 1:
              radio.push(item);
              break;
            case 2:
              check.push(item);
              break;
            case 3:
              ofList.push(item);
              break;
            default:
              break;
          }
        }
        this.radioList = radio;
        this.checkList = check;
        this.ofList = ofList;
      });
    },
    // 返回
    backClick() {
      this.$router.go(-1)
      // this.$router.push({ path: "/paperManage", name: "paperManage" });
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