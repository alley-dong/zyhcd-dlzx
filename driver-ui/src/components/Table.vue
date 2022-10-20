// 封装的table组件
<template>
  <div class="fake-table">
    <van-row class="fake-table__head">
      <!-- 表格头部一个循环：循环渲染每一列的列名 -->
      <van-col
        class="col"
        v-for="(item, index) in headData"
        :key="index"
        :span="item.span"
        >{{ item.name }}</van-col
      >
    </van-row>
    <!-- 表格内容两重循环：外层循环渲染行，内层循环渲染每一行的每一列的内容 -->
    <van-row
      class="fake-table__body"
      v-for="(item, index) in bodyData"
      :key="index"
    >
      <template v-for="(colItem, colIndex) in headData">
        <van-col
          class="col"
          :span="colItem.span"
          :key="colIndex"
          v-if="colItem.prop"
        >
          <!-- 这里其实重点只要关注v-model绑定的属性名。其他属性看项目具体需要，比如说placeholder也可以绑定：placeholder="item.placeholder"等等，只要传进来的数组符合数据结构就行 -->
          <van-field
            v-model="item[colItem.prop]"
            placeholder="请输入名称"
            :disabled="item.editDisable"
          />
        </van-col>
        <!-- 这里是项目需要，不让事件冒泡，如果不需要可以去掉.stop修饰符。而capture就是用来捕获slot进来的元素的事件的，因为在父组件定义事件的话无法取到这里循环的colIndex -->
        <van-col
          class="col"
          :span="colItem.span"
          :key="colIndex"
          @click.stop.capture="doThis($event, index)"
          v-else
        >
          <slot></slot>
        </van-col>
      </template>
    </van-row>
  </div>
</template>
<script>
export default {
  props: {
    headData: {
      type: Array,
      default: () => [],
    },
    bodyData: {
      type: Array,
      default: () => [],
    },
  },
  methods: {
    doThis(e, rowIdx) {
      if (e.target.id === 'edit') {
        this.$emit('edit', rowIdx)
      } else if (e.target.id === 'delete') {
        this.$emit('delete', rowIdx)
      }
    },
  },
}
</script>
<style lang="less" scoped>
@inputPaddingLeft: 5px;
@colPaddingLeft: 10px;

.fake-table /deep/ .van-field__control {
  padding-left: @inputPaddingLeft;
}

.col /deep/ .van-cell {
  padding: 0;
}

.col /deep/ .input-diabled {
  border: none;
}

.col /deep/ .input-abled {
  border: 1px solid #dddddd;
}

.table-col {
  height: 100%;
  display: flex;
  justify-content: flex-start;
  align-items: flex-start;
  font-family: PingFangSC-Medium, PingFang SC;
  color: #313131;
}

.fake-table {
  margin: 0 12px;
  &__head,
  &__body {
    border: 1px solid #dddddd;
    &:not(:last-child) {
      border-bottom: none;
    }
  }
  &__head {
    height: 40px;
    background: #f5faff;
    .col:extend(.table-col) {
      font-size: 14px;
      font-weight: 500;
      line-height: 20px;
      padding: @colPaddingLeft;
    }
  }
  &__body {
    .col:extend(.table-col) {
      font-size: 13px;
      font-weight: 400;
      line-height: 18px;
      padding: @colPaddingLeft @colPaddingLeft @colPaddingLeft
        calc(@colPaddingLeft - @inputPaddingLeft);
      &:last-child {
        padding-left: @inputPaddingLeft;
      }
    }
  }
}
</style>
