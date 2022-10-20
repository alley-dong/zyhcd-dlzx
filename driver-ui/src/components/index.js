/**
 * 表格时间格式化
 */
export function formatDate(cellValue, time) {
    if (cellValue == null || cellValue == "") return "";
    var date = new Date(cellValue)
    var year = date.getFullYear()
    var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1
    var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate()
    var hours = date.getHours() < 10 ? '0' + date.getHours() : date.getHours()
    var minutes = date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()
    var seconds = date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds()
    switch (time) {
        case 'yyyy':
            return year
        case 'yyyy-MM':
            return year + '-' + month
        case 'MM':
            return  month
        case 'yyyy-MM-dd':
            return year + '-' + month + '-' + day
        default:
            return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
    }
    // return year + '-' + month + '-' + day + ' ' + hours + ':' + minutes + ':' + seconds
    

}


// 相差天数
export function timeFn(start, end) {
    var dateBegin = new Date(start.replace(/-/g, "/"));//将-转化为/，使用new Date
    var dateEnd = new Date(end.replace(/-/g, "/"));//获取当前时间
    var dateDiff = dateEnd.getTime() - dateBegin.getTime();//时间差的毫秒数
    var dayDiff = Math.floor(dateDiff / (24 * 3600 * 1000));//计算出相差天数
    return dayDiff
}

// 后端传来日期转年月日
export function dateReplace(val, name) {
    switch (name) {
        case '日':
            return val.vactionTime.replace('-', '年').replace('-', '月') + '日'
        case '月':
            return val.substring(0, val.lastIndexOf('-')).replace('-', '年') + '月'
        case '年':
            return val.substring(0, val.indexOf('-')) + '年'
        default:
            break;
    }
}