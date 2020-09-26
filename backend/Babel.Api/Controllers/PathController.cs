﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Babel.Api.Base;
using Babel.Api.Graph;
using Babel.Db.Base;
using Babel.Db.Models;
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
                sourceRoom = await _roomService.Get(sourceRoomName);
            if (targetRoom == null)
                targetRoom = await _roomService.Get(targetRoomName);

                if (sourceRoom == null)
                return NotFound("Исходная комната не найдена");
            if (targetRoom == null)
                return NotFound("Целевая комната не найдена");

            var rooms = (await _roomService.Get()).Where(x => x.Type == "room");
            var doors = await _entityService.GetEntitiesByType("door");
            var stairs = await _entityService.GetEntitiesByType("stairs");
            var elevators = await _entityService.GetEntitiesByType("elevator");

            // строим граф соединений всех комнат
            var graph = new Graph<BasePathable>();
            foreach (var baseRoom in rooms) // для начала добавим все комнаты
            {
                graph.AddVertex(baseRoom);
            }

            foreach (var door in doors) // потом добавим все двери
            {
                graph.AddVertex(door);
                foreach (var room in rooms)     // и посмотрим, если дверь пересекается с комнатой, то добавим грань
                {
                    bool doesIntersects = DoesIntersects(door, room);
                    if (doesIntersects)
                    {
                        graph.AddEdge(new Tuple<BasePathable, BasePathable>(door, room));
                        graph.AddEdge(new Tuple<BasePathable, BasePathable>(room, door));
                    }
                }
            }

            var bfsAlgo = new GraphBfsAlgorithm();
            var shortestPathFunc = bfsAlgo.ShortestPathFunction(graph, rooms.First(x => x.Id == sourceRoom.Id));
            try
            {
                var shortestPath = shortestPathFunc(rooms.First(x => x.Id == targetRoom.Id));

                var result = shortestPath;


                return JsonResponse.New(result);
            }
            catch (Exception e)
            {
                Console.WriteLine(e);
                throw;
            }
            return BadRequest("Не удалось построить маршрут");
        }

        private bool DoesIntersects(Entity door, BaseRoom room)
        {
            int doorRadius = 10;
            var distX = Math.Abs(door.Position.X - room.Position.X - room.Size.Width / 2);
            var distY = Math.Abs(door.Position.Y - room.Position.Y - room.Size.Height / 2);

            if (distX > (room.Size.Width / 2 + doorRadius)) { return false; }
            if (distY > (room.Size.Height / 2 + doorRadius)) { return false; }

            if (distX <= (room.Size.Width / 2)) { return true; }
            if (distY <= (room.Size.Height / 2)) { return true; }

            var dx = distX - room.Size.Width / 2;
            var dy = distY - room.Size.Height / 2;
            return (dx * dx + dy * dy <= (doorRadius * doorRadius));


            /*int doorRadius = 10 ;
            Vector distance = new Vector();
            distance.X = Math.Abs(door.Position.X - room.Position.X + 10);
            distance.Y = Math.Abs(door.Position.Y - room.Position.Y + 10);

            if (distance.X > (room.Size.Width / 2 + doorRadius)) { return false; }
            if (distance.Y > (room.Size.Height / 2 + doorRadius)) { return false; }

            if (distance.X <= (room.Size.Width / 2)) { return true; }
            if (distance.Y <= (room.Size.Height / 2)) { return true; }

            var distanceSquared = Math.Pow((distance.X - room.Size.Width / 2), 2) +
                                  Math.Pow((distance.Y - room.Size.Height / 2), 2);

            return (distanceSquared <= (doorRadius * doorRadius));*/
        }
    }
}
