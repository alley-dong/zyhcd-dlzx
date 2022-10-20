<template>
  <div>
    <Header :show="true" :showRight="false"></Header>
    <!-- <Header :show="true" :showRight="false" buttonShow @handleClick="addClick"></Header> -->
    <div v-if="my_show" class="app-container" style="margin-bottom:1rem">
      <!-- <div style="margin-top: 20px">入职笔试</div> -->
      <!-- 单选 -->
      <div class="radio">
        <div class="title">单选题</div>
        <!-- <van-radio-group v-model="radio" >
          <van-radio class="text" icon-size='16' name="1" label-position='left'>A、转弯时，速度过快</van-radio>
          <van-radio class="text" icon-size='16' name="2" label-position='left'>单选框 2</van-radio>
        </van-radio-group> -->
        <van-radio-group
          v-for="(item, index) in radioList"
          :key="item.id"
          v-model="item.answer"
          :disabled="myDisabled"
        >
          <!-- <div class="text"></div> -->

          <van-cell class="text" :title="index + 1 + '、' + item.name" />
          <van-cell-group>
            <van-cell class="text" :title="'A、' + item.optionA" clickable>
              <template #right-icon>
                <van-radio name="A" icon-size="16" />
              </template>
            </van-cell>
            <van-cell class="text" :title="'B、' + item.optionB" clickable>
              <template #right-icon>
                <van-radio name="B" icon-size="16" />
              </template>
            </van-cell>
            <van-cell class="text" :title="'C、' + item.optionC" clickable>
              <template #right-icon>
                <van-radio name="C" icon-size="16" />
              </template>
            </van-cell>
            <van-cell class="text" :title="'D、' + item.optionD" clickable>
              <template #right-icon>
                <van-radio name="D" icon-size="16" />
              </template>
            </van-cell>
          </van-cell-group>
        </van-radio-group>
      </div>
      <!-- 多选 -->
      <div class="check">
        <div class="title">多选题</div>
        <van-checkbox-group
          v-for="(item, index) in checkList"
          :key="item.id"
          v-model="item.answer"
          :disabled="myDisabled"
        >
          <van-cell class="text" :title="index + 1 + '、' + item.name" />
          <van-cell-group>
            <van-cell
              v-for="(data, index) in item.list"
              clickable
              :key="index"
              :title="data.text + '、' + data.option"
              class="text"
            >
              <template #right-icon>
                <van-checkbox
                  shape="square"
                  icon-size="16"
                  :name="data.text"
                  ref="checkboxes"
                />
              </template>
            </van-cell>
          </van-cell-group>
        </van-checkbox-group>
      </div>
      <!-- 判断 -->

      <div class="of">
        <div class="title">判断题</div>
        <van-radio-group
          v-for="(item, index) in ofList"
          :key="item.id"
          v-model="item.answer"
          :disabled="myDisabled"
        >
          <van-cell class="text" :title="index + 1 + '、' + item.name" />
          <van-cell-group>
            <van-cell class="text" :title="'A、对' " clickable>
              <template #right-icon>
                <van-radio name="A" icon-size="16" />
              </template>
            </van-cell>
            <van-cell class="text" :title="'B、错'" clickable>
              <template #right-icon>
                <van-radio name="B" icon-size="16" />
              </template>
            </van-cell>
          </van-cell-group>
        </van-radio-group>
      </div>
      <div v-if="scoreHe">
        <div class="branch">
          <span class="span">单选题得分:</span> {{ scoreDan }}
        </div>
        <div class="branch">
          <span class="span">多选题得分:</span> {{ scoreDuo }}
        </div>
        <div class="branch">
          <span class="span">判断题得分:</span> {{ scorePan }}
        </div>
        <div class="branch"><span class="span"> 总分:</span> {{ scoreHe }}</div>
      </div>
    </div>
    <div v-else>
      <van-empty description="您未分配考试试卷!" />
    </div>
    <van-button
      v-if="my_show && !myDisabled"
      round
      type="info"
      style="margin-bottom:1rem;width:50%"
      @click="handleClick"
      >提交</van-button
    >
  </div>
</template>

<script>
import Header from "@/components/Header";
import {
  driverProblemB01List,
  driverProblemB02List,
} from "@/api/writtenExamination";
// import { Switch } from "vant";
export default {
  data() {
    return {
      my_show: true,
      list: ["A、起动机移位", "B、起动机驱动齿轮或齿圈损坏"],
      radio: null,
      result: [],
      radioList: [], //多选数组
      checkList: [], //多选数组
      ofList: [], //判断数组
      scoreDan: undefined,
      scoreDuo: undefined,
      scoreHe: undefined,
      scorePan: undefined,
      myDisabled: false,
    };
  },
  mounted() {
    driverProblemB01List({})
      .then((res) => {
        // type 1当选 2多选 3判断
        console.log(res);
        if (res.rows.length==0) {
          this.my_show = false
        }
        let { scoreDan, scoreDuo, scoreHe, scorePan } = res.rows[0];
        this.scoreDan = scoreDan;
        this.scoreDuo = scoreDuo;
        this.scoreHe = scoreHe;
        this.scorePan = scorePan;
        let bool = res.rows[0].status == 1;
          console.log(res.rows[0].list);
        let list = res.rows[0].list || res.rows
        for (const item of list) {
          // item.paperId =
          switch (item.type) {
            case 1:
              item.answer = bool ? item.answer : undefined;
              this.radioList.push(item);
              break;
            case 2:
              item.answer = bool ? item.answer.split(",") : undefined;
              item.list = [];

              item.list.push({
                option: item.optionA,
                text: "A",
              });
              item.list.push({
                option: item.optionB,
                text: "B",
              });
              item.list.push({
                option: item.optionC,
                text: "C",
              });
              item.list.push({
                option: item.optionD,
                text: "D",
              });
              this.checkList.push(item);
              break;
            case 3:
              item.answer = bool ? item.answer : undefined;
              this.ofList.push(item);
              break;
            default:
              break;
          }
          if (!item.paperId) {
            this.myDisabled = true;
            continue;
          }
        }
      })
      .catch((err) => {
        // debugger
        if (err.code == 500) {
          this.my_show = false;
        }
      });
  },
  components: {
    Header,
  },
  methods: {
    // 新增
    addClick() {
      console.log(111);
    },
    // 提交  0205e78d-d3dc-4ee2-ae4e-a4e90b8adc16
    handleClick() {
      let list = JSON.parse(JSON.stringify(this.checkList));
      for (const item of list) {
        if (typeof item.answer == "object") {
          item.answer = item.answer.join(",");
        }
      }
      let arr = [...this.radioList, ...list, ...this.ofList];
      console.log(arr);
      driverProblemB02List(arr).then(() => this.$router.go(-1));
    },
    handleChange(val) {
      console.log(val);
    },
    toggle(index) {
      this.$refs.checkboxes[index].toggle();
      debugger
    },
  },
};
</script>

<style lang="scss" scoped>
.title {
  font-weight: 600;
  text-align: left;
  padding-left: 0.5rem;
  font-size: 0.3rem;
  margin: 0.2rem 0;
}
.text {
  text-align: left;
  font-size: 0.25rem;
}
.span {
  width: 50%;
  text-align: right;
  font-size: 0.25rem;
  margin-right: 1rem;
}
.branch {
  margin-top: 20px;
}
</style>
