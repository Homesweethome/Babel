import axios from '@/plugins/axios'

async function getAllHomeElements() {
    return await axios.get('/room')
}

function setHomeElements(homeElement) {
    return axios.post('/room', {
        level: homeElement.floor,
        type: homeElement.type,
        positionStart: {
            x: homeElement.positionStart.x,
            y: homeElement.positionStart.y
        },
        size: {
            width: homeElement.size.width,
            height: homeElement.size.width
        },
        description: homeElement.description,
        name: homeElement.name
    })
}

function deleteHomeElement(idHomeElement) {
    return axios.delete('/room'+idHomeElement)
}

async function getFloors() {
    return await axios.get('/level')
}

function addFloors(floors) {
    return axios.post('/level', {
        title: floors.title,
        value: floors.value,
    })
}
export default {
    getAllHomeElements,
    setHomeElements,
    deleteHomeElement,
    addFloors,
    getFloors
}
