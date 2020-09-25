using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Json;
using System.Threading.Tasks;
using AutoMapper;
using Babel.Api.Base;
using Babel.Api.Dto;
using Babel.Api.Dto.Room;
using Babel.Db.Models;
using Babel.Db.Services;
using Microsoft.AspNetCore.Mvc;

namespace Babel.Api.Controllers
{
    [ApiController]
    [Route("level")]
    public class LevelController: ControllerBase
    {
        private readonly RoomService _roomService;
        private readonly LevelService _levelService;

        private readonly IMapper _mapper;

        public LevelController(RoomService roomService, LevelService levelService, IMapper mapper)
        {
            _roomService = roomService;
            _levelService = levelService;

            _mapper = mapper;
        }

        [HttpGet]
        [Route("")]
        public async Task<IActionResult> GetLevels()
        {
            var converted = _mapper.Map<List<LevelDto>>(await _levelService.Get());
            var result = JsonSerializer.Serialize(converted);
            return JsonResponse.New(result);
        }

        [HttpPost]
        [Route("")]
        public async Task<IActionResult> AddLevel(LevelDto levelDto)
        {
            var level = _mapper.Map<Level>(levelDto);
            level.Id = Guid.NewGuid().ToString();
            var result = await _levelService.Create(level);

            return JsonResponse.New(_mapper.Map<LevelDto>(result));
        }

        [HttpDelete]
        [Route("levelId:alpha")]
        public async Task<IActionResult> RemoveLevel(string levelId)
        {
            var level = await _levelService.Get(levelId);
            if (level == null)
                return NotFound();
            await _roomService.RemoveRoomsByLevel(levelId);
            await _levelService.Remove(levelId);
            return JsonResponse.New("ok");
        }

        [HttpPost, HttpPut]
        [Route("background/{levelId:alpha}")]
        public async Task<IActionResult> SetBackground(string levelId, string image)
        {
            var level = await _levelService.Get(levelId);
            if (level == null)
                return NotFound();
            level.Image = image;
            await _levelService.Update(levelId, level);
            return JsonResponse.New(level);
        }

        [HttpDelete]
        [Route("background/{levelId:alpha}")]
        public async Task<IActionResult> DeleteBackground(string levelId)
        {
            var level = await _levelService.Get(levelId);
            if (level == null)
                return NotFound();
            level.Image = "";
            await _levelService.Update(levelId, level);
            return JsonResponse.New(level);
        }
    }
}
