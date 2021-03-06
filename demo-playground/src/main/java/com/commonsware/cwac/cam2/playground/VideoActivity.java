/***
 Copyright (c) 2015 CommonsWare, LLC

 Licensed under the Apache License, Version 2.0 (the "License"); you may
 not use this file except in compliance with the License. You may obtain
 a copy of the License at http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 */

package com.commonsware.cwac.cam2.playground;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

public class VideoActivity extends Activity
    implements VideoFragment.Contract {
  private static final int REQUEST_VIDEO=1337;
  private static final String TAG_PLAYGROUND=VideoFragment.class.getCanonicalName();
  private VideoFragment playground=null;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    playground=(VideoFragment)getFragmentManager().findFragmentByTag(TAG_PLAYGROUND);

    if (playground==null) {
      playground=new VideoFragment();
      getFragmentManager()
          .beginTransaction()
          .add(android.R.id.content, playground, TAG_PLAYGROUND)
          .commit();
    }
  }

  @Override
  public void takeVideo(Intent i) {
    startActivityForResult(i, REQUEST_VIDEO);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode==REQUEST_VIDEO) {
      if (resultCode == Activity.RESULT_OK) {
        startActivity(new Intent(Intent.ACTION_VIEW, data.getData()));
      }
    }
  }
}
