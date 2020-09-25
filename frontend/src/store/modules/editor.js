const state = {
    homeElements: [], //элементы здания - комната, стена ...
    nodeElements: [], // объекты поиска - стелаж, пк, диван,
    selectHomeElement: false,
    selectNodeElement: false,
    modeEditor: 'select', //draw - рисование select - выбор обьекта для редактирования
    drawTypeElement: 'node',
    floors: [],
    selectFloorId: 1,
}

const getters = {

}

const actions = {

}

const mutations = {

}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
}
