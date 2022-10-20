'use strict'
const webpack = require('webpack')
const path = require('path')
const port = process.env.port || process.env.npm_config_port || 80 // 端口
console.log('.process.env.VUE_APP_BASE_API', process.env.VUE_APP_BASE_API)

function resolve (dir) {
    return path.join(__dirname, dir)
}
module.exports = {
  lintOnSave: true,
  publicPath: '/',
  outputDir: "dist",
  assetsDir: "static",
  css: {
    loaderOptions: {
      postcss: {
        plugins: [
          require('postcss-pxtorem')({
            rootValue: 50, // 换算的基数
            propList: ['*'],
          }),
        ]
      }
    }
  },
  chainWebpack: config => {
    // 优化moment 去掉国际化内容
    config
      .plugin('ignore')
      // 忽略/moment/locale下的所有文件
      .use(new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/))
  },
  // devServer: {
  //   port: 8080,
  //   proxy: {
  //     [process.env.VUE_APP_BASE_API]: {
  //       target: `http://115.29.205.180:8089`,
  //       changeOrigin: true,
  //       pathRewrite: {
  //           ['^' + process.env.VUE_APP_BASE_API]: ''
  //       }
  //   }
  //   }
  // },
  configureWebpack: {
    name: '驾驶员管理',
    resolve: {
        alias: {
            '@': resolve('src')
        }
    }
},
  devServer: {
      open:true,
      proxy: {
      // '^/api': {
      //   target: '<url>',
      //   ws: true,
      //   changeOrigin: true
      // },
      '^/foo': {
        target: 'http://115.29.205.180:8089/',
        // target:  'http://192.168.1.103:8089/',
        changeOrigin: true,
        pathRewrite: {
          ['^' + '/foo']: ''
        }
      },
    }
  },
  productionSourceMap: false,
}
