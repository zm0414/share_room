function formatTime(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()


  return [year, month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
}

function formatDate(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  console.log(month)
  var day = date.getDate()
  return [year, month, day].map(formatNumber).join('-')
}

function formatYmd(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()
  return year + month + day;
}

function formatAddDate(date, days) {
  var year = date.getFullYear()
  date.setDate(date.getDate() + days);
  var month = date.getMonth() + 1
  var day = date.getDate()
  return [year, month, day].map(formatNumber).join('-')
}

function formatAddYmd(date, days) {
  var year = date.getFullYear()
  date.setDate(date.getDate() + days);
  var month = date.getMonth() + 1
  var day = date.getDate()
  return [year, month, day].map(formatNumber).join('')
}

function formatMonthDay(date, days) {
  var year = date.getFullYear()
  date.setDate(date.getDate() + days);
  var month = date.getMonth() + 1
  var day = date.getDate()
  return month + "月" + day + "日";
}

function formatHm(date) {
  var hour = date.getHours()
  var minute = date.getMinutes()
  return [hour, minute].map(formatNumber).join(':')
}

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

var weeks = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"];

function getWeek(date, days) {
  date.setDate(date.getDate() + days);
  return weeks[date.getDay()];
}

module.exports = {
  formatTime: formatTime,
  formatDate: formatDate,
  formatAddDate: formatAddDate,
  formatHm: formatHm,
  formatMonthDay: formatMonthDay,
  getWeek: getWeek,
  formatYmd: formatYmd
}