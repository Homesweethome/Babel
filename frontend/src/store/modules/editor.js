import APIEditorServices from "@/services/APIEditorServices";

const state = {
    homeElements: [],
    nodeElements: [],
    selectHomeElement: false,
    selectNode: false,
    selectedNode: {},
    selectedHomeElement: {},
    modeEditor: 'draw', //draw - рисование, select  выбор обьекта для редактирования, searche
    drawTypeElement: 'node',
    floors: [
        {
            title: '1',
            id: 1,
            pathImage: '../../assets/scheme/1.png'
        }
    ],
    selectFloorId: 1,
    searchRoom: [],
    path: {},
    books: "{\"maxLastResult\":\"90000\",\"size\":\"42\",\"result\":[{\"author\":\"Калихман И. Л.\",\"Title\":\"Линейная алгебра и программирование\",\"P_Date\":\"1967\",\"Publishing\":\"Высшая школа\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Виленкин И. В., Гробер В. М.\",\"Title\":\"Высшая математика. Линейная алгебра. Аналитическая геометрия. Дифференциальное и интегральное исчисление\",\"P_Date\":\"2011\",\"Publishing\":\"Феникс\",\"location\":\"Ростов-на-Дону\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Кузьмичев А. И.\",\"Title\":\"Линейная алгебра - 2\",\"P_Date\":\"2008\",\"Publishing\":\"Новосибирский государственный педагогический университет\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Кузьмичев А. И.\",\"Title\":\"Линейная алгебра - 1\",\"P_Date\":\"2009\",\"Publishing\":\"Новосибирский государственный педагогический университет\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"\",\"Title\":\"Численные методы анализа и линейной алгебры. Ч. 2. Линейная алгебра\",\"P_Date\":\"2009\",\"Publishing\":\"Новосибирский государственный университет\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Болгов В. А.\",\"Title\":\"Сборник задач по математике для втузов. [Ч. 1]. Линейная алгебра и основы математического анализа\",\"P_Date\":\"1986\",\"Publishing\":\"Наука\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Кострикин А. И.\",\"Title\":\"Введение в алгебру. Ч. 2. Линейная алгебра\",\"P_Date\":\"2000\",\"Publishing\":\"Физматлит\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Гусак А. А.\",\"Title\":\"Аналитическая геометрия и линейная алгебра\",\"P_Date\":\"2001\",\"Publishing\":\"ТетраСистемс\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Постников М. М.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"1986\",\"Publishing\":\"Наука, Главная редакция физико-математической литературы\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Кострикин А. И., Манин Ю. И.\",\"Title\":\"Линейная алгебра и геометрия\",\"P_Date\":\"1986\",\"Publishing\":\"Наука, Главная редакция физико-математической литературы\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Кадомцев С. Б.\",\"Title\":\"Аналитическая геометрия и линейная алгебра\",\"P_Date\":\"2001\",\"Publishing\":\"Физматлит\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Ильин В. А.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"1978\",\"Publishing\":\"Наука, Главная редакция физико-математической литературы\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Идельсон А. В., Блюмкина И. А.\",\"Title\":\"Математика для экономистов. Т. 1. Аналитическая геометрия. Линейная алгебра\",\"P_Date\":\"2000\",\"Publishing\":\"ИНФРА-М\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Головина Л. И.\",\"Title\":\"Линейная алгебра и некоторые ее приложения\",\"P_Date\":\"1985\",\"Publishing\":\"Наука, Главная редакция физико-математической литературы\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Крутицкая Н. Ч., Тихонравов А. В., Шишкин А. А.\",\"Title\":\"Аналитическая геометрия и линейная алгебра с приложениями\",\"P_Date\":\"1991\",\"Publishing\":\"Издательство МГУ\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Ильин В. А., Позняк Э. Г.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"1984\",\"Publishing\":\"Наука, Главная редакция физико-математической литературы\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Григорьев С. Г.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"1999\",\"Publishing\":\"ИВЦ \\\"Маркетинг\\\"\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Солодовников А. С., Торопова Г. А.\",\"Title\":\"Линейная алгебра с элементами аналитической геометрии\",\"P_Date\":\"1987\",\"Publishing\":\"Высшая школа\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Дудоладов Л. С., Бертик И. А., Серяков В. М., Вахромеева Т. В.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"2001\",\"Publishing\":\"Новосибирский государственный архитектурно-строительный университет\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Канатников А. Н., Крищенко А. П.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"1999\",\"Publishing\":\"Издательство МГТУ им. Н. Э. Баумана\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Мальцев И. А.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"2001\",\"Publishing\":\"Издательство Института математики\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Рублев А. Н.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"1968\",\"Publishing\":\"Высшая школа\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Ляпин Е. С.\",\"Title\":\"Алгебра и теория чисел. Ч. 2. Линейная алгебра и полиномы\",\"P_Date\":\"1978\",\"Publishing\":\"Просвещение\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Ивлева А. М., Прилуцкая П. И., Черных И. Д.\",\"Title\":\"Линейная алгебра. Аналитическая геометрия\",\"P_Date\":\"2000\",\"Publishing\":\"Новосибирский государственный технический университет\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"\",\"Title\":\"Алгебраический реферативный сборник за 1941-1946 гг.. Вып. 1. Алгебра полиномов. Линейная алгебра. Теория полей. Теория колец и алгебр\",\"P_Date\":\"1948\",\"Publishing\":\"Издательство иностранной литературы\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Бэр Р.\",\"Title\":\"Линейная алгебра и проективная геометрия\",\"P_Date\":\"1955\",\"Publishing\":\"Издательство иностранной литературы\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Гильдерман Ю. И.\",\"Title\":\"Лекции по высшей математике для биологов. Ч. 2-3. Многомерные пространства. Линейная алгебра. Анализ функций многих переменных\",\"P_Date\":\"1967\",\"Publishing\":\"Новосибирский государственный университет\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Дроздов Н. Д.\",\"Title\":\"Линейная алгебра в теории уравнения измерений\",\"P_Date\":\"1972\",\"Publishing\":\"Недра\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Дьедонне Ж.\",\"Title\":\"Линейная алгебра и элементарная геометрия\",\"P_Date\":\"1972\",\"Publishing\":\"Наука, Главная редакция физико-математической литературы\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Ефимов Н. В., Розендорн Э. Р.\",\"Title\":\"Линейная алгебра и многомерная геометрия\",\"P_Date\":\"1970\",\"Publishing\":\"Наука, Главная редакция физико-математической литературы\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"\",\"Title\":\"Линейная алгебра и геометрия\",\"P_Date\":\"1967\",\"Publishing\":\"Просвещение\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Стренг Г.\",\"Title\":\"Линейная алгебра и ее применения\",\"P_Date\":\"1980\",\"Publishing\":\"Мир\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Тышкевич Р. И.\",\"Title\":\"Линейная алгебра и аналитическая геометрия\",\"P_Date\":\"1968\",\"Publishing\":\"Вышэйшая школа\",\"location\":\"Минск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Хедли Дж.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"1966\",\"Publishing\":\"Высшая школа\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Буров А. Н., Соснина Э. Г.\",\"Title\":\"Линейная алгебра и аналитическая геометрия\",\"P_Date\":\"2012\",\"Publishing\":\"Новосибирский государственный технический университет\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Рапоцевич Е. А.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"2012\",\"Publishing\":\"Сибирская академия государственной службы\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Брюханов О. В., Гузевский Л. Г.\",\"Title\":\"Линейная алгебра\",\"P_Date\":\"2013\",\"Publishing\":\"Сибирский университет потребительской кооперации\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Павский В. А., Павский К. В.\",\"Title\":\"Линейная алгебра в теории вычислительных систем\",\"P_Date\":\"2013\",\"Publishing\":\"Сибирский государственный университет телекоммуникаций и информатики\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Брюханов О. В., Гузевский Л. Г.\",\"Title\":\"Математика. Линейная алгебра\",\"P_Date\":\"2013\",\"Publishing\":\"Сибирский университет потребительской кооперации\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Ивлева А. М., Прилуцкая П. И., Черных И. Д.\",\"Title\":\"Линейная алгебра. Аналитическая геометрия\",\"P_Date\":\"2014\",\"Publishing\":\"Новосибирский государственный технический университет\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Плотникова Е. Г., Иванов А. П., Логинова В. В., Морозова А. В.\",\"Title\":\"Линейная алгебра и аналитическая геометрия\",\"P_Date\":\"2018\",\"Publishing\":\"Юрайт\",\"location\":\"Москва\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"},{\"author\":\"Ивлева А. М., Прилуцкая П. И., Черных И. Д.\",\"Title\":\"Линейная алгебра. Аналитическая геометрия\",\"P_Date\":\"2019\",\"Publishing\":\"Новосибирский государственный технический университет\",\"location\":\"Новосибирск\",\"LINK\":\"\",\"Adress\":\"г.Новосибирск, Советская, 6, Отраслевой зал\"}],\"iddb\":[{\"number\":\"4\",\"title\":\"Книги и продолжающиеся издания\"}]}"
}

const getters = {

}

const actions = {
    async get_books({commit},search){
      let books = await APIEditorServices.getBooks(search.id, search.author)
        commit('SAVE_BOOKS', books.data)
    },
    select_home_element_for_searche({commit}, data){
        commit('SAVE_SEARCHE_HOME_ELEMENT', data)
    },
    async search_path({state, commit}){
        let path = await APIEditorServices.searchePath(
            state.searchRoom[0].id,
            state.searchRoom[1].id
        )
        commit('SAVE_PATH', path)
    },
    set_active_floor({commit, dispatch}, id){
        commit('SAVE_ACTIVE_FLOOR', id)
        dispatch('get_all_nodes')
    },
    add_floor({commit}, data){
        let newFloors = APIEditorServices.addFloors(data)
        commit('SAVE_FLOOR', newFloors)
    },
    async add_image_for_floor({dispatch, state}, data){
         await APIEditorServices.addImageFloor(state.selectFloorId, data)
        dispatch('get_all_floors')

    },
    async get_all_floors({commit, dispatch}){
      let allFloors = await APIEditorServices.getFloors()
      commit('SAVE_FLOORS', allFloors.data)
        dispatch('set_active_floor', allFloors.data[0].id)
    },
    async delete_floor({dispatch,state},id){
        await APIEditorServices.deleteFloor(state.selectFloorId)
        dispatch('get_all_floors')
    },
    async add_home_element({commit, dispatch, state}, data){
        data.floor = state.selectFloorId
        if (!data.id){
            data.type = state.drawTypeElement
        }
            //let newElement = {}
        await APIEditorServices.setHomeElements(data)
        // switch (state.drawTypeElement) {
        //     case 'room' : newElement = await APIEditorServices.setHomeElements(data)
        //         break;
        // }
        dispatch('get_all_home_elements')
        //commit('SAVE_NEW_HOME_ELEMENT', newElement)
    },
    async add_node({dispatch,state}, data) {
        data.floor = state.selectFloorId
        data.type = state.drawTypeElement
        APIEditorServices.addNode(data)
        dispatch('get_all_nodes')
        //commit('SAVE_NEW_NODE', data)
    },
    // update_home_element({commit}, data){
    //
    // },
    // update_node({commit}, data){
    //
    // },
    select_node({commit, dispatch}, data){
        dispatch('unselect_home_element')
        commit('SAVE_SELECTED_NODE', data)
    },
    select_home_element({commit, dispatch}, data){
        dispatch('unselect_node')
        commit('SAVE_SELECTED_HOME_ELEMENT', data)
    },
    async delete_home_element({ dispatch, state}, data){
        const numberHomeElement = state.homeElements.indexOf(data)
        await APIEditorServices.deleteHomeElement(data.id)
        dispatch('get_all_home_elements')
        dispatch('unselect')
        //commit('DELETE_HOME_ELEMENT', numberHomeElement)
    },
    async delete_node({dispatch}, data){
        await APIEditorServices.deleteNode(data.id)
        // commit('DELETE_NODE_ELEMENT', numberNode)
        dispatch('get_all_nodes')
        dispatch('unselect')
    },
    async get_all_home_elements({commit}){
        let homeElement = await APIEditorServices.getAllHomeElements()
        commit('SAVE_HOME_ELEMENTS', homeElement.data)
    },
    async get_all_nodes({commit, state}){
        let nodes = await APIEditorServices.getAllNode(state.selectFloorId)
        commit('SAVE_NODES', nodes.data)
    },
    set_draw_mode_editor({commit}){
        commit('SAVE_EDITOR_MODE', 'draw')
    },
    set_select_mode_editor({commit}){
        commit('SAVE_EDITOR_MODE', 'select')
    },
    set_searche_mode({commit}){
      commit('SAVE_EDITOR_MODE', 'search')
    },
    set_draw_type_element({commit}, typeElement){
        commit('SAVE_DRAW_TYPE_ELEMENT', typeElement)
    },
    unselect_home_element({commit}){
        commit('UNSELECT_HOME_ELEMENT')
    },
    unselect_node({commit}){
        commit('UNSELECT_NODE')
    },
    unselect({commit}){
        commit('UNSELECT')
    }
}


const mutations = {
    SAVE_BOOKS(state, payload){
      state.books = payload
    },
    SAVE_PATH(state, path){
      state.path = path
    },
    SAVE_SEARCHE_HOME_ELEMENT(state, payload){
        state.searchRoom.push(payload)
        if (state.searchRoom.length>2) {
            state.searchRoom = state.searchRoom.shift()
        }
    },
    SAVE_ACTIVE_FLOOR(state, payload){
        state.selectFloorId = payload
    },
    SAVE_FLOOR(state, payload){
        state.floors.push(payload)
    },
    SAVE_HOME_ELEMENTS(state, payload){
      state.homeElements = payload
    },
    SAVE_NEW_HOME_ELEMENT(state, payload){
        state.homeElements.push(payload)
    },
    SAVE_NEW_NODE(state, payload){
        state.nodeElements.push(payload)
    },
    SAVE_SELECTED_NODE(state, payload){
        state.selectNode = true
        state.selectedNode = payload
    },
    SAVE_SELECTED_HOME_ELEMENT(state, payload) {
        state.selectHomeElement = true
        state.selectedHomeElement = payload
    },
    SAVE_EDITOR_MODE(state, payload){
        state.modeEditor = payload
    },
    SAVE_DRAW_TYPE_ELEMENT(state, payload){
        state.drawTypeElement = payload
    },
    UNSELECT_HOME_ELEMENT(state){
        state.selectedHomeElement = {}
        state.selectHomeElement = false
    },
    UNSELECT_NODE(state){
        state.selectedNode = {}
        state.selectNode = false
    },
    UNSELECT(state){
        state.selectedHomeElement = {}
        state.selectHomeElement = false
        state.selectedNode = {}
        state.selectNode = false
    },
    DELETE_HOME_ELEMENT(state, numberHomeElement){
        state.homeElements.splice(numberHomeElement, 1)
    },
    DELETE_NODE_ELEMENT(state, numberNode){
        state.nodeElements.splice(numberNode, 1)
    },
    SAVE_FLOORS(state, payload){
        state.floors = payload
    },
    SAVE_NODES(state, payload){
        state.nodeElements = payload
    }
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations,
}
