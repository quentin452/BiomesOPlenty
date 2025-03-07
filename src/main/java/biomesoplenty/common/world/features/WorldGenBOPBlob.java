package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenBOPBlob extends WorldGeneratorBOP
{
    private Block field_150545_a;
    private int field_150544_b;

    public WorldGenBOPBlob(Block p_i45450_1_, int p_i45450_2_)
    {
        super(false);
        this.field_150545_a = p_i45450_1_;
        this.field_150544_b = p_i45450_2_;
    }

    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        while (true)
        {
            if (par4 > 3)
            {
                label63:
                {
                    if (!par1World.isAirBlock(par3, par4 - 1, par5))
                    {
                        Block block = par1World.getBlock(par3, par4 - 1, par5);

                        if (block == Blocks.grass || block == Blocks.dirt || block == Blocks.stone)
                        {
                            break label63;
                        }
                    }

                    --par4;
                    continue;
                }
            }

            if (par4 <= 3)
            {
                return false;
            }

            int k2 = this.field_150544_b;

            for (int l = 0; k2 >= 0 && l < 3; ++l)
            {
                int i1 = k2 + par2Random.nextInt(2);
                int j1 = k2 + par2Random.nextInt(2);
                int k1 = k2 + par2Random.nextInt(2);
                float f = (float)(i1 + j1 + k1) * 0.333F + 0.5F;

                for (int l1 = par3 - i1; l1 <= par3 + i1; ++l1)
                {
                    for (int i2 = par5 - k1; i2 <= par5 + k1; ++i2)
                    {
                        for (int j2 = par4 - j1; j2 <= par4 + j1; ++j2)
                        {
                            float f1 = (float)(l1 - par3);
                            float f2 = (float)(i2 - par5);
                            float f3 = (float)(j2 - par4);

                            if (f1 * f1 + f2 * f2 + f3 * f3 <= f * f)
                            {
                                par1World.setBlock(l1, j2, i2, this.field_150545_a, 0, 4);
                            }
                        }
                    }
                }

                par3 += -(k2 + 1) + par2Random.nextInt(2 + k2 * 2);
                par5 += -(k2 + 1) + par2Random.nextInt(2 + k2 * 2);
                par4 += 0 - par2Random.nextInt(2);
            }

            return true;
        }
    }

	@Override
    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
	{
		for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature(featureName); i++)
		{
			int randX = x + random.nextInt(16) + 8;
			int randZ = z + random.nextInt(16) + 8;
			int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

			this.generate(world, random, randX, randY, randZ);
		}
	}
}
