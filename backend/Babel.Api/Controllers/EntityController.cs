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

        [HttpGet]
        [Route("")]
        public async Task<IActionResult> GetEntities()
        {
            var entities = await _entityService.Get();
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
    }
}
