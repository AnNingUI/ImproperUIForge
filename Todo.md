- I18n [x]
    - 在标签块中添加类似如下$t插槽并开启i18n功能
    inner-text: "AAA $t{ui.text.improper_ui_interactives}"
    is-i18n: true

- SVG Render [x]
    - 在标签块中添加类似如下$svg插槽
    - background-image: "$svg{iui_forge:textures/svg/test.svg, 100%, 100%}"

- Render Pages Events [ ]
  - What is that?
    - 提供一个UI展示时可被监听的事件，用于拿到当前页面的所有元素，以及可被修改值
  - In Java Mod [ ]
     - 待定
  - In Kubejs [ ]
     - 已经初步确认写法
    ```js
    let homescreen = new ResourceLocation("kubejs:homescreen")
    ImportantUIEvents.pageInTick(homescreen, (ev) => {
     let elements = ev.getElements()
     let map = ev.getMutable()
     let intValue = map.getInt("intValue")
     // 其他Getter待定
    })
    ```
    
- Network Utils [ ]
  - What is that?
    - 提供一个网络发包的工具类，
  - Why
    - 原生的发包机制过于麻烦，以及在kjs更是麻烦经常出现客户端要写两个事件才能处理服务端要求的渲染，
    一个事件用来接收网络包，然后复制给作用域外的变量，另一个事件就是渲染事件来读取这个变量，让人真的
    的很迷惑
  - 大概思路
    每一段存储一堆Type Map, 通过指定key，类型，值发包后那一段通过事件接受存储到 Type Map中
    这样接收端就只需要访问对应Map就能拿到数据了

- Component System [ ? ]
  - What is that?
    - 现在的编写方式时模板与代码分离，很不符合声明式开发UI，或许在将来我会开发一种类Web Component的开发方式
    但由于难度较大以及Java语言的局限性，暂时还不确定是否会开发