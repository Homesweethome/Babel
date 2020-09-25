using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;
using AutoMapper;
using Babel.Api.Base;
using Babel.Api.Dto.Targets;
using Babel.Db.Models.Targets;
using Babel.Db.Services;
using Microsoft.AspNetCore.Mvc;

namespace Babel.Api.Controllers
{
    [ApiController]
    [Route("target")]
    public class TargetController: ControllerBase
    {
        private readonly RoomService _roomService;
        private readonly TargetService _targetService;
        private readonly LevelService _levelService;

        private readonly IMapper _mapper;

        public TargetController(RoomService roomService, TargetService targetService, 
            LevelService levelService, IMapper mapper)
        {
            _roomService = roomService;
            _targetService = targetService;
            _levelService = levelService;

            _mapper = mapper;
        }

        /// <summary>
        /// Получить список нероходимых объектов на этаже
        /// </summary>
        /// <param name="level"></param>
        /// <returns></returns>
        [HttpGet]
        [Route("{level:alpha}")]
        public async Task<IActionResult> GetTargets(string level)
        {
            var targets = await _targetService.GetTargetByLevel(level);

            var converted = _mapper.Map<List<TargetDto>>(targets);
            var result = JsonSerializer.Serialize(converted);
            return JsonResponse.New(result);
        }

        /// <summary>
        /// Добавить непроходимый объект на этаж
        /// </summary>
        /// <param name="level"></param>
        /// <param name="target"></param>
        /// <returns></returns>
        [HttpPost]
        [Route("{level:alpha}")]
        public async Task<IActionResult> AddTarget(string level, TargetDto target)
        {
            var l = await _levelService.Get(level);
            if (l == null)
                return BadRequest("Выбран несуществующий этаж");

            target.Level = level;
            target.Id = Guid.NewGuid().ToString();
            var targetDb = _mapper.Map<Target>(target);
            var result = await _targetService.Create(targetDb);
            return JsonResponse.New(result);
        }

        /// <summary>
        /// Обновить поисковые аттрибуты для целевого объекта
        /// </summary>
        /// <param name="targetId"></param>
        /// <param name="attributes"></param>
        /// <returns></returns>
        [HttpPut]
        [Route("changeattributes/{targetId:alpha}")]
        public async Task<IActionResult> UpdateAttributes(string targetId, List<string> attributes)
        {
            var target = await _targetService.Get(targetId);
            if (target == null)
                return BadRequest("Нечему менять аттрибуты");

            target.Attributes = attributes;

            await _targetService.Update(targetId, target);

            return JsonResponse.New(target);
        }

        /// <summary>
        /// Изменить фотографию для целевого объекта
        /// </summary>
        [HttpPut, HttpPost]
        [Route("photo/{id:alpha}")]
        public async Task<IActionResult> SetPhoto(string id, string photo)
        {
            var target = await _targetService.Get(id);
            target.Photo = photo;
            await _targetService.Update(id, target);
            return JsonResponse.New(_mapper.Map<TargetDto>(target));
        }
    }
}
