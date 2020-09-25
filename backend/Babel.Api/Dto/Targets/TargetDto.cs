using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using Babel.Db.Models.Targets;

namespace Babel.Api.Dto.Targets
{
    [AutoMap(typeof(Target))]
    [AutoMap(typeof(Target), ReverseMap = true)]
    public class TargetDto: BaseDto
    {
        public string Type { get; set; }
        public string Name { get; set; }
        public string Level { get; set; }
        public List<string> Attributes { get; set; }
        public PositionDto Position { get; set; }
        public SizeDto Size { get; set; }
        public string Photo { get; set; }
    }
}
