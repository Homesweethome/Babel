using System.Collections.Generic;
using Babel.Db.Base;

namespace Babel.Db.Models.Rooms
{
    public class BaseRoom: BaseModel
    {
        public string Description { get; set; }
        public string Level { get; set; }
        public Vector PositionStart { get; set; }
        public Size Size { get; set; }
        public string Photo { get; set; }
        public string Name { get; set; }
        public List<string> Attributes { get; set; }
        public string Type { get; set; }
    }
}
