module.exports = {
    // https://cli.vuejs.org/config/#devserver-proxy
    devServer: {
        port: 3000,
        proxy: {
            '/api': {
                target: 'http://localhost:8000/',
                ws: true,
                changeOrigin: true
            }
        },
        // publicPath: '',

        // outputDir: '../main/resources/static', // 빌드경로
      
        // chainWebpack(config) { //빌드 시 빌드되어 나오는 js파일을 js폴더 아래로 묶어 빌드한다
      
        //   config.output.filename("js/[name].js"); 
      
        // },
    }
}