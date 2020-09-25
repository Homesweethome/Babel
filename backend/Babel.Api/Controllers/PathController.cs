using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Babel.Api.Graph;
using Babel.Db.Base;
using Babel.Db.Models.Entities;
using Babel.Db.Models.Rooms;
using Babel.Db.Services;
using Microsoft.AspNetCore.Mvc;

namespace Babel.Api.Controllers
{
    [ApiController]
    [Route("path")]
    public class PathController: ControllerBase
    {
        private readonly RoomService _roomService;
        private readonly EntityService _entityService;

        public PathController(RoomService roomService, EntityService entityService)
        {
            _roomService = roomService;
            _entityService = entityService;
        }

        /// <summary>
        /// Получить путь из комнаты в комнату
        /// </summary>
        /// <param name="sourceRoomName"></param>
        /// <param name="targetRoomName"></param>
        /// <returns></returns>
        [HttpGet]
        public async Task<IActionResult> GetPathToRoom(string sourceRoomName, string targetRoomName)
        {
            var sourceRoom = await _roomService.GetRoomByName(sourceRoomName);
            var targetRoom = await _roomService.GetRoomByName(targetRoomName);

            if (sourceRoom == null)
                return NotFound("Исходная комната не найдена");
            if (targetRoom == null)
                return NotFound("Целевая комната не найдена");

            var rooms = await _roomService.Get();
            var doors = await _entityService.GetEntitiesByType("door");
            var stairs = await _entityService.GetEntitiesByType("stair");
            var elevators = await _entityService.GetEntitiesByType("elevator");

            var graph = new Graph<BaseRoom>();
            foreach (var baseRoom in rooms)
            {
                graph.AddVertex(baseRoom);
            }

            foreach (var door in doors)
            {
                foreach (var room in rooms)
                {
                    bool doesIntersects = DoesIntersects(door, room);
                }
            }


            return null;
        }

        private bool DoesIntersects(Entity door, BaseRoom room)
        {
            int doorRadius = 10 ;
            Vector distance = new Vector();
            distance.X = Math.Abs(door.Position.X - room.PositionStart.X);
            distance.Y = Math.Abs(door.Position.Y - room.PositionStart.Y);

            if (distance.X > (room.Size.Width / 2 + doorRadius)) { return false; }
            if (distance.Y > (room.Size.Height / 2 + doorRadius)) { return false; }

            if (distance.X <= (room.Size.Width / 2)) { return true; }
            if (distance.Y <= (room.Size.Height / 2)) { return true; }

            var distanceSquared = Math.Pow((distance.X - room.Size.Width / 2), 2) +
                                  Math.Pow((distance.Y - room.Size.Height / 2), 2);

            return (distanceSquared <= (doorRadius ^ 2));
        }
    }
}
