using System;
using System.Collections.Generic;
using System.Text;
using Babel.Db.Base;

namespace Babel.Db.Models.Targets
{
    public class Target: BaseModel
    {
        public string Type { get; set; }
        public string Name { get; set; }
        public string Level { get; set; }
        public List<string> Attributes { get; set; }
        public Vector Position { get; set; }
        public Size Size { get; set; }
        public string Photo { get; set; }
    }
}
