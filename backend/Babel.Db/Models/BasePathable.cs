using System;
using System.Collections.Generic;
using System.Text;
using Babel.Db.Base;

namespace Babel.Db.Models
{
    public class BasePathable: BaseModel
    {
        public Size Size { get; set; }
        public Vector Position { get; set; }
    }
}
