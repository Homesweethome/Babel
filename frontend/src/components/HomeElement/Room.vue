<template>
    <g>
        <rect
                :x="data.positionStart.x"
                :y="data.positionStart.y"
                :width="data.size.width"
                :height="data.size.height"
                :stroke="selectedRoom"
                stroke-width="5"
                stroke-dasharray="20"
                fill="#eee"
                fill-opacity=".4"
        />
        <foreignObject  :x="data.positionStart.x"
                        :y="data.positionStart.y"
                        :width="data.size.width"
                        :height="data.size.height">
            <div style="color: red;">
                Новая комната
            </div>
        </foreignObject>
    </g>
</template>

<script>
    export default {
        name: "Room",
        props: {
            data: Object
        },
    }
</script>

<style scoped>

</style>
