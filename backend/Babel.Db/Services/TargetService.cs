using System;
using System.Collections.Generic;
using System.Text;
using System.Threading.Tasks;
using Babel.Db.Models.Targets;
using Babel.Db.Settings;
using MongoDB.Driver;

namespace Babel.Db.Services
{
    public class TargetService: BaseMongoService<Target>
    {
        public TargetService(IBaseDatabaseSettings settings) : base(settings)
        {
        }

        public async Task<List<Target>> GetTargetByLevel(string levelId)
        {
            var rooms = (await _collection.FindAsync(x => x.Level == levelId)).ToList();
            return rooms;
        }

        public async Task RemoveTargetByLevel(string levelId)
        {
            await _collection.DeleteManyAsync(x => x.Level == levelId);
        }
    }
}
