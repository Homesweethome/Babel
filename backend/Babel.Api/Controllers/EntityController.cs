using System;
using System.Collections.Generic;
using System.Text.Json;
using System.Threading.Tasks;
using AutoMapper;
using Babel.Api.Base;
using Babel.Api.Dto.Entity;
using Babel.Db.Models.Entities;
using Babel.Db.Services;
using Microsoft.AspNetCore.Mvc;

namespace Babel.Api.Controllers
{
    [ApiController]
    [Route("entity")]
    public class EntityController: ControllerBase
    {
        private readonly RoomService _roomService;
        private readonly EntityService _entityService;
        private readonly IMapper _mapper;

        public EntityController(RoomService roomService,
            EntityService entityService,
            IMapper mapper)
        {
            _roomService = roomService;
            _entityService = entityService;
            _mapper = mapper;
        }

        /// <summary>
        /// Получить список проходимых сущностей для этажа
        /// </summary>
        /// <returns></returns>
        [HttpGet]
        [Route("{level:alpha}")]
        public async Task<IActionResult> GetEntities(string level)
        {
            var entities = await _entityService.GetEntitiesByLevel(level);
            var converted = _mapper.Map<List<EntityDto>>(entities);
            var result = JsonSerializer.Serialize(converted);

            return JsonResponse.New(result);
        }

        /// <summary>
        /// Добавление сущности
        /// </summary>
        /// <param name="entityDto"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("")]
        public async Task<IActionResult> AddEntity(EntityDto entityDto)
        {
            var entity = _mapper.Map<Entity>(entityDto);
            entity.Id = Guid.NewGuid().ToString();
            var result = await _entityService.Create(entity);

            return JsonResponse.New(result);
        }

        /// <summary>
        /// Удалить проходимую сущность
        /// </summary>
        /// <param name="entityId"></param>
        /// <returns></returns>
        [HttpDelete]
        [Route("{entityId:alpha}")]
        public async Task<IActionResult> DeleteEntity(string entityId)
        {
            var entity = await _entityService.Get(entityId);
            if (entity == null)
                return NotFound("Сущность не найдена");
            await _entityService.Remove(entity);
            return JsonResponse.New("ok");
        }

        /// <summary>
        /// Связать проходимую сущность с другой сущностью
        /// </summary>
        [HttpPost]
        [Route("bind/{sourceId:alpha}")]
        public async Task<IActionResult> BindEntity(string sourceId, string targetId)
        {
            var sourceEntity = await _entityService.Get(sourceId);
            var targetEntity = await _entityService.Get(targetId);

            if (sourceEntity == null || targetEntity == null)
                return NotFound("Сущность не найдена");

            if (sourceEntity.Type != targetEntity.Type)
                return BadRequest("Типы связываемых сущностей не совпадают");

            if (sourceEntity.Type != "stairs" && sourceEntity.Type != "elevator" &&
                targetEntity.Type != "stairs" && targetEntity.Type != "elevator")
                return BadRequest("Попытка связать несвязанные сущности");

            return JsonResponse.New("ok");
        }
    }
}
